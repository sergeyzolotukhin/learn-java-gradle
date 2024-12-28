package ua.in.sz.executor.service.completable.future.impl;

import ua.in.sz.executor.service.completable.future.MyCompletableFuture;

import java.util.concurrent.ForkJoinTask;
import java.util.function.Supplier;

public final class AsyncSupply<T> extends ForkJoinTask<Void>
        implements Runnable, AsynchronousCompletionTask {

    MyCompletableFuture<T> dep;
    Supplier<? extends T> fn;

    public AsyncSupply(MyCompletableFuture<T> dep, Supplier<? extends T> fn) {
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
