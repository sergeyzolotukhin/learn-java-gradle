package ua.in.sz.executor.service.completable.future;

import ua.in.sz.executor.service.completable.future.impl.MyCompletion;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Objects;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

// java.util.concurrent.FutureTask.cancel
public class MyCompletableFuture<T> implements Future<T>, MyCompletionStage<T> {

    volatile Object result;       // Either the result or boxed AltResult
    volatile MyCompletion stack;    // Top of Treiber stack of dependent actions


    final boolean completeValue(T t) {
        return RESULT.compareAndSet(this, null, (t == null) ? NIL : t);
    }

    final void postComplete() {
        /*
         * On each step, variable f holds current dependents to pop
         * and run.  It is extended along only one path at a time,
         * pushing others to avoid unbounded recursion.
         */
        MyCompletableFuture<?> f = this;
        MyCompletion h;

        while (     (h = f.stack) != null
                ||  (f != this && (h = (f = this).stack) != null)
        ) {

            MyCompletableFuture<?> d;
            MyCompletion t;
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

    final boolean completeThrowable(Throwable x) {
        return RESULT.compareAndSet(this, null, encodeThrowable(x));
    }

    static AltResult encodeThrowable(Throwable x) {
        return new AltResult((x instanceof CompletionException) ? x : new CompletionException(x));
    }

    final void pushStack(MyCompletion c) {
        do {
        } while (!tryPushStack(c));
    }

    final boolean tryPushStack(MyCompletion c) {
        MyCompletion h = stack;
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

    public interface AsynchronousCompletionTask {
    }

    @SuppressWarnings("serial")
    static final class AsyncSupply<T> extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask {
        MyCompletableFuture<T> dep;
        Supplier<? extends T> fn;

        AsyncSupply(MyCompletableFuture<T> dep, Supplier<? extends T> fn) {
            this.dep = dep;
            this.fn = fn;
        }

        public Void getRawResult() {
            return null;
        }

        public void setRawResult(Void v) {
        }

        public boolean exec() {
            run();
            return false;
        }

        public void run() {
            MyCompletableFuture<T> d;
            Supplier<? extends T> f;

            if ((d = dep) != null && (f = fn) != null) {
                dep = null;
                fn = null;

                if (d.result == null) {
                    try {
                        d.completeValue(f.get());
                    } catch (Throwable ex) {
                        d.completeThrowable(ex);
                    }
                }
                d.postComplete();
            }
        }
    }

    // Modes for Completion.tryFire. Signedness matters.
    public static final int SYNC   =  0;
    public static final int ASYNC  =  1;
    public static final int NESTED = -1;



    static final class AltResult { // See above
        final Throwable ex;        // null only for NIL
        AltResult(Throwable x) { this.ex = x; }
    }

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
            STACK = l.findVarHandle(MyCompletableFuture.class, "stack", MyCompletion.class);
            NEXT = l.findVarHandle(MyCompletion.class, "next", MyCompletion.class);
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
