package ua.in.sz.pattern.gof.template.method.classic;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTimeLoggingRunnable implements Runnable {
    public void runWithTimeLogging() {
        Stopwatch watch = Stopwatch.createStarted();

        run();

        watch.stop();

        log.info("Execution took: {} ", watch);
    }
}
