package ua.in.sz.executor.service.completable.future.impl;

public class AltResult {
    public final Throwable ex;        // null only for NIL

    public AltResult(Throwable x) {
        this.ex = x;
    }
}
