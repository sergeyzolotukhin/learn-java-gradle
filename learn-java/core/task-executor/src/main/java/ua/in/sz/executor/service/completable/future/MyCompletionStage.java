package ua.in.sz.executor.service.completable.future;


public interface MyCompletionStage<T> {
    public MyCompletableFuture<T> toCompletableFuture();
}
