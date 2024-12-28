package ua.in.sz.executor.service.completable.future.impl;

import ua.in.sz.executor.service.completable.future.MyCompletableFuture;

import java.util.concurrent.ForkJoinTask;

public abstract class Completion extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask {
    public volatile Completion next;      // Treiber stack link

    /**
     * Performs completion action if triggered, returning a
     * dependent that may need propagation, if one exists.
     *
     * @param mode SYNC, ASYNC, or NESTED
     */
    public abstract MyCompletableFuture<?> tryFire(int mode);

    /**
     * Returns true if possibly still triggerable. Used by cleanStack.
     */
    public abstract boolean isLive();

    public final void run() {
        tryFire(MyCompletableFuture.ASYNC);
    }

    public final boolean exec() {
        tryFire(MyCompletableFuture.ASYNC);
        return false;
    }

    public final Void getRawResult() {
        return null;
    }

    public final void setRawResult(Void v) {
    }
}