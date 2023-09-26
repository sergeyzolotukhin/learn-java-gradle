package ua.in.sz.h2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadPoolExecutorMain {
    public static void main(String[] args) {
        try (ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 2,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new MyThreadFactory()
        )
        ) {

            List<Future<?>> list = new ArrayList<>();

            for (int i = 0; i < 6; i++) {
                final int no = i;
                try {
                    executor.execute(() -> {
                    });
//                executor.invokeAny(Collections.emptyList());
//                executor.invokeAll(Collections.emptyList());
                    Future<?> future = executor.submit(new Runnable() {
                        @SneakyThrows
                        @Override
                        public void run() {
                            String originName = Thread.currentThread().getName();

                            try {
                                Thread.currentThread().setName("My name");
                                log.info("Run {}", no);
                                TimeUnit.SECONDS.sleep(no);
                                log.info("Ran {}", no);
                            } finally {
                                Thread.currentThread().setName(originName);
                            }

                        }
                    });
                    list.add(future);
                } catch (RejectedExecutionException r) {
                    log.info("Rejected {}", no);
                }
            }

            for (Future<?> f : list) {
                try {
                    f.get(2, TimeUnit.SECONDS);
                } catch (ExecutionException e) {
                    log.info("Execution exception");
                } catch (InterruptedException e) {
                    log.info("Interrupted exception");
                } catch (TimeoutException e) {
                    log.info("Timeout exception");
                }
            }

            log.info("End");

            executor.shutdown();
        }
    }

    private static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        @SuppressWarnings("removal")
        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            Thread t = new MyThread(group, runnable,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);

            if (t.isDaemon()) {
                t.setDaemon(false);
            }

            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }

            return t;
        }
    }


    public static class MyThread extends Thread {
        public MyThread(ThreadGroup group, Runnable task, String name, long stackSize) {
            super(group, task, name, stackSize);
        }
    }
}
