package ua.in.sz.executor.service.completable.future.impl;

import ua.in.sz.executor.service.completable.future.MyCompletableFuture;

public class UniRelay<U, T extends U> extends UniCompletion<T,U> {
    public UniRelay(MyCompletableFuture<U> dep, MyCompletableFuture<T> src) {
        super(null, dep, src);
    }

    public final MyCompletableFuture<U> tryFire(int mode) {
        MyCompletableFuture<U> d; MyCompletableFuture<T> a; Object r;
        if ((a = src) == null || (r = a.result) == null
                || (d = dep) == null)
            return null;
        if (d.result == null)
            d.completeRelay(r);
        src = null; dep = null;
        return d.postFire(a, mode);
    }
}