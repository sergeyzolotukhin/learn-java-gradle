package ua.in.sz.executor.service.completable.future;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.executor.service.completable.future.impl.AltResult;
import ua.in.sz.executor.service.completable.future.impl.AsyncSupply;
import ua.in.sz.executor.service.completable.future.impl.Completion;
import ua.in.sz.executor.service.completable.future.impl.UniCompose;
import ua.in.sz.executor.service.completable.future.impl.UniRelay;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;
import java.util.function.Supplier;

// java.util.concurrent.FutureTask.cancel
@Slf4j
public class MyCompletableFuture<T> implements Future<T>, MyCompletionStage<T> {

    public volatile Object result;      // Either the result or boxed AltResult
    /**
     * This is reference to a head of a stack of a Completion
     * Completion (next) -> Completion (next) -> .... -> Completion
     */
    volatile Completion stack;          // Top of Treiber stack of dependent actions

    public final boolean completeValue(T t) {
        return RESULT.compareAndSet(this, null, (t == null) ? NIL : t);
    }

    public final boolean completeThrowable(Throwable x) {
        return RESULT.compareAndSet(this, null, encodeThrowable(x));
    }

    public final void postComplete() {
        /*
         * On each step, variable f holds current dependents to pop
         * and run.  It is extended along only one path at a time,
         * pushing others to avoid unbounded recursion.
         */
        MyCompletableFuture<?> f = this;
        Completion h;

        while (     (h = f.stack) != null
                ||  (f != this && (h = (f = this).stack) != null)
        ) {

            MyCompletableFuture<?> d;
            Completion t;
            if (STACK.compareAndSet(f, h, t = h.next)) {
                if (t != null) {
                    if (f != this) {
                        pushStack(h);
                        continue;
                    }
                    NEXT.compareAndSet(h, t, null); // try to detach
                }
                f = (d = h.tryFire(NESTED)) == null ? this : d;
            }
        }
    }

    public final MyCompletableFuture<T> postFire(MyCompletableFuture<?> a, int mode) {
        if (a != null && a.stack != null) {
            Object r;
            if ((r = a.result) == null) {
                a.cleanStack();
            }

            if (mode >= 0 && (r != null || a.result != null)) {
                a.postComplete();
            }
        }

        if (result != null && stack != null) {
            if (mode < 0) {
                return this;
            } else {
                postComplete();
            }
        }

        return null;
    }

    final void cleanStack() {
        Completion p = stack;
        // ensure head of stack live
        for (boolean unlinked = false;;) {
            if (p == null)
                return;
            else if (p.isLive()) {
                if (unlinked)
                    return;
                else
                    break;
            }
            else if (STACK.weakCompareAndSet(this, p, (p = p.next)))
                unlinked = true;
            else
                p = stack;
        }
        // try to unlink first non-live
        for (Completion q = p.next; q != null;) {
            Completion s = q.next;
            if (q.isLive()) {
                p = q;
                q = s;
            } else if (NEXT.weakCompareAndSet(p, q, s))
                break;
            else
                q = p.next;
        }
    }

    static AltResult encodeThrowable(Throwable x) {
        return new AltResult((x instanceof CompletionException) ? x : new CompletionException(x));
    }

    public MyCompletableFuture<T> toCompletableFuture() {
        return this;
    }

    // ================================================================================================================
    // stack managments

    final void pushStack(Completion c) {
        do {
        } while (!tryPushStack(c));
    }

    final boolean tryPushStack(Completion c) {
        Completion h = stack;
        NEXT.set(c, h);         // CAS piggyback
        return STACK.compareAndSet(this, h, c);
    }

    public final void unipush(Completion c) {
        if (c != null) {
            while (!tryPushStack(c)) {
                if (result != null) {
                    NEXT.set(c, null);
                    break;
                }
            }
            if (result != null)
                c.tryFire(SYNC);
        }
    }

    // ================================================================================================================
    // then compose
    // ================================================================================================================

    public <U> MyCompletableFuture<U> thenComposeAsync(Function<? super T, ? extends MyCompletionStage<U>> fn, Executor executor) {
        return uniComposeStage(screenExecutor(executor), fn);
    }

    private <V> MyCompletableFuture<V> uniComposeStage(Executor e, Function<? super T, ? extends MyCompletionStage<V>> f) {
        if (f == null) {
            throw new NullPointerException();
        }

        MyCompletableFuture<V> d = newIncompleteFuture();
        Object r, s;
        Throwable x;

        if ((r = result) == null) {
            unipush(new UniCompose<T, V>(e, d, this, f));
        } else {
            if (r instanceof AltResult) {
                if ((x = ((AltResult)r).ex) != null) {
                    d.result = encodeThrowable(x, r);
                    return d;
                }
                r = null;
            }

            try {
                if (e != null) {
                    e.execute(new UniCompose<T, V>(null, d, this, f));
                } else {
                    @SuppressWarnings("unchecked") T t = (T) r;
                    MyCompletableFuture<V> g = f.apply(t).toCompletableFuture();
                    if ((s = g.result) != null)
                        d.result = encodeRelay(s);
                    else
                        g.unipush(new UniRelay<V,V>(d, g));
                }
            } catch (Throwable ex) {
                d.result = encodeThrowable(ex);
            }
        }

        return d;
    }

    public <U> MyCompletableFuture<U> newIncompleteFuture() {
        return new MyCompletableFuture<U>();
    }

    static Object encodeThrowable(Throwable x, Object r) {
        if (!(x instanceof CompletionException))
            x = new CompletionException(x);
        else if (r instanceof AltResult && x == ((AltResult)r).ex)
            return r;
        return new AltResult(x);
    }

    public final boolean completeThrowable(Throwable x, Object r) {
        return RESULT.compareAndSet(this, null, encodeThrowable(x, r));
    }

    static Object encodeRelay(Object r) {
        Throwable x;
        if (r instanceof AltResult
                && (x = ((AltResult)r).ex) != null
                && !(x instanceof CompletionException))
            r = new AltResult(new CompletionException(x));
        return r;
    }

    public final boolean completeRelay(Object r) {
        return RESULT.compareAndSet(this, null, encodeRelay(r));
    }

    // ================================================================================================================
    // interface Future
    // ================================================================================================================

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        // TODO implement
        return null;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    // ================================================================================================================
    // static methods
    // ================================================================================================================

    public static <U> MyCompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) {
        return asyncSupplyStage(screenExecutor(executor), supplier);
    }

    static <U> MyCompletableFuture<U> asyncSupplyStage(Executor e, Supplier<U> f) {
        if (f == null) {
            throw new NullPointerException();
        }

        MyCompletableFuture<U> d = new MyCompletableFuture<U>();
        e.execute(new AsyncSupply<U>(d, f));
        return d;
    }

    // Modes for Completion.tryFire. Signedness matters.
    public static final int SYNC   =  0;
    public static final int ASYNC  =  1;
    public static final int NESTED = -1;


    /** The encoding of the null value. */
    static final AltResult NIL = new AltResult(null);

    // VarHandle mechanics
    private static final VarHandle RESULT;
    private static final VarHandle STACK;
    private static final VarHandle NEXT;
    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            RESULT = l.findVarHandle(MyCompletableFuture.class, "result", Object.class);
            STACK = l.findVarHandle(MyCompletableFuture.class, "stack", Completion.class);
            NEXT = l.findVarHandle(Completion.class, "next", Completion.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }

        // Reduce the risk of rare disastrous classloading in first call to
        // LockSupport.park: https://bugs.openjdk.org/browse/JDK-8074773
        Class<?> ensureLoaded = LockSupport.class;
    }

    // ================================================================================================================
    // thread pools
    // ================================================================================================================

    private static final boolean USE_COMMON_POOL = (ForkJoinPool.getCommonPoolParallelism() > 1);
    private static final Executor ASYNC_POOL = USE_COMMON_POOL ?
            ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();

    static Executor screenExecutor(Executor e) {
        if (!USE_COMMON_POOL && e == ForkJoinPool.commonPool())
            return ASYNC_POOL;
        if (e == null) throw new NullPointerException();
        return e;
    }

    private static final class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable r) {
            Objects.requireNonNull(r);
            new Thread(r).start();
        }
    }
}
