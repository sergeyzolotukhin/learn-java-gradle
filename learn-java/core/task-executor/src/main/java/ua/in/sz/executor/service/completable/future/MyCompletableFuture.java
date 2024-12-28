package ua.in.sz.executor.service.completable.future;

import ua.in.sz.executor.service.completable.future.impl.AltResult;
import ua.in.sz.executor.service.completable.future.impl.AsyncSupply;
import ua.in.sz.executor.service.completable.future.impl.Completion;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Objects;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

// java.util.concurrent.FutureTask.cancel
public class MyCompletableFuture<T> implements Future<T>, MyCompletionStage<T> {

    public volatile Object result;       // Either the result or boxed AltResult
    volatile Completion stack;    // Top of Treiber stack of dependent actions


    public final boolean completeValue(T t) {
        return RESULT.compareAndSet(this, null, (t == null) ? NIL : t);
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

    public final boolean completeThrowable(Throwable x) {
        return RESULT.compareAndSet(this, null, encodeThrowable(x));
    }

    static AltResult encodeThrowable(Throwable x) {
        return new AltResult((x instanceof CompletionException) ? x : new CompletionException(x));
    }

    final void pushStack(Completion c) {
        do {
        } while (!tryPushStack(c));
    }

    final boolean tryPushStack(Completion c) {
        Completion h = stack;
        NEXT.set(c, h);         // CAS piggyback
        return STACK.compareAndSet(this, h, c);
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
        if (f == null) throw new NullPointerException();
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
