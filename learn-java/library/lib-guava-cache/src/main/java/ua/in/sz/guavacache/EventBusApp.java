package ua.in.sz.guavacache;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class EventBusApp {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        AsyncEventBus eventBus = new AsyncEventBus(executor);

        EventListener listener = new EventListener();
        eventBus.register(listener);

        for (int i = 0; i < 20; i++) {
            eventBus.post("String Event");
        }
        log.info("EventBusApp main finished");

//        TimeUnit.SECONDS.sleep(3);

        eventBus.unregister(listener);
        executor.shutdown();

        log.info("EventBusApp main finished");
    }

    public static class EventListener {
        @Subscribe
        public void stringEvent(String event) {
            log.info("recive event: {}", event);
        }
    }
}
