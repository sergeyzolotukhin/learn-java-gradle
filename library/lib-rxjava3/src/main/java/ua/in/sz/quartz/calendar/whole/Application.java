package ua.in.sz.quartz.calendar.whole;

import lombok.extern.slf4j.Slf4j;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Application {
    public static void main(String[] args) {
        Observable<Long> obs = Observable.intervalRange(1L, 5L, 0L, 2L, TimeUnit.SECONDS); //1

        subWithObserver(obs);
        subWithConsumers(obs);
    }

    private static <T> void subWithObserver(Observable<T> obs) {
        obs.blockingSubscribe(new Observer<>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.disposable = d;
            }

            @Override
            public void onNext(@NonNull T item) {
                log.info("Received in Observer: {}", item);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                disposable.dispose();
                log.error("Error", e);
            }

            @Override
            public void onComplete() {
                disposable.dispose();
                log.info("Complete");
            }
        });
    }

    private static <T> void subWithConsumers(Observable<T> obs) {
        obs.blockingSubscribe(
                item -> log.info("Received in Consumer: {}", item),
                error -> log.error("Error", error),
                () -> log.info("Complete")
        );
    }

}
