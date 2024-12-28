package ua.in.sz.executor.service.completable.future.impl;

import ua.in.sz.executor.service.completable.future.MyCompletableFuture;

import java.util.concurrent.Executor;

public abstract class UniCompletion<T,V> extends Completion {
    Executor executor;                  // executor to use (null if none)
    MyCompletableFuture<V> dep;         // the dependent to complete
    MyCompletableFuture<T> src;         // source for action

    UniCompletion(Executor executor, MyCompletableFuture<V> dep, MyCompletableFuture<T> src) {
        this.executor = executor;
        this.dep = dep;
        this.src = src;
    }

    /**
     * Returns true if action can be run. Call only when known to
     * be triggerable. Uses FJ tag bit to ensure that only one
     * thread claims ownership.  If async, starts as task -- a
     * later call to tryFire will run action.
     */
    final boolean claim() {
        Executor e = executor;
        if (compareAndSetForkJoinTaskTag((short)0, (short)1)) {
            if (e == null) {
                return true;
            }

            executor = null; // disable
            e.execute(this);
        }
        return false;
    }

    public final boolean isLive() { return dep != null; }
}