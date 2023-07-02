package ua.in.sz.quartz.calendar.whole;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
    public static void main(String[] args) {
        Observable<String> observer = Observable.just("Hello");
        Disposable subscribe = observer.subscribe(s -> log.info("Result: {}", s));
        subscribe.dispose();
    }
}
