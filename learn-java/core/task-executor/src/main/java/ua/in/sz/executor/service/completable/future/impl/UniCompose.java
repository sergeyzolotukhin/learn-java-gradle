package ua.in.sz.executor.service.completable.future.impl;

import ua.in.sz.executor.service.completable.future.MyCompletableFuture;
import ua.in.sz.executor.service.completable.future.MyCompletionStage;

import java.util.concurrent.Executor;
import java.util.function.Function;

public class UniCompose <T,V> extends UniCompletion<T,V> {
    Function<? super T, ? extends MyCompletionStage<V>> fn;

    public UniCompose(Executor executor,
                      MyCompletableFuture<V> dep,
                      MyCompletableFuture<T> src,
                      Function<? super T, ? extends MyCompletionStage<V>> fn
    ) {
        super(executor, dep, src);
        this.fn = fn;
    }

    public final MyCompletableFuture<V> tryFire(int mode) {
        MyCompletableFuture<V> d;
        MyCompletableFuture<T> a;
        Function<? super T, ? extends MyCompletionStage<V>> f;
        Object r;
        Throwable x;

        if ((a = src) == null || (r = a.result) == null
                || (d = dep) == null || (f = fn) == null)
            return null;

        tryComplete:
        if (d.result == null) {
            if (r instanceof AltResult) {
                if ((x = ((AltResult)r).ex) != null) {
                    d.completeThrowable(x, r);
                    break tryComplete;
                }
                r = null;
            }

            try {
                if (mode <= 0 && !claim())
                    return null;
                @SuppressWarnings("unchecked") T t = (T) r;
                MyCompletableFuture<V> g = f.apply(t).toCompletableFuture();
                if ((r = g.result) != null)
                    d.completeRelay(r);
                else {
                    g.unipush(new UniRelay<V,V>(d, g));
                    if (d.result == null)
                        return null;
                }
            } catch (Throwable ex) {
                d.completeThrowable(ex);
            }
        }

        src = null;
        dep = null;
        fn = null;

        return d.postFire(a, mode);
    }
}