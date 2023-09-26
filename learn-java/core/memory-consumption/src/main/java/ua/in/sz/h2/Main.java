package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("ALL")
@Slf4j
public class Main {
    private static final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        long m = get();
        log.info("Memory: {}", m);

        long myLeak = getMemoryLeakAverage(() -> {
            StaticTest.main();
        }, 20);
        log.info( "Memory Leak Average {} bytes", myLeak);
    }

    public static long get() {
        final int count = 100_000;
        Object[] objectsArray = new Object[count];
        long initHeap = 0;

        runGC(); // warm-up
        initHeap = getCurrentUsedMemory();

        for (int i = 0; i < count; i++) {
            objectsArray[i] = new String();
        }
        runGC();
        long finalHeap = getCurrentUsedMemory();

        // this `for` loop is to make sure objectsArray instances are in-use.
        // otherwise, it'll be cleaned up by the previous runGC()
        for (int i = 0; i < count; ++i) {
            objectsArray[i] = null;
        }
        runGC();
        return (finalHeap - initHeap) / count;
    }

    private static void runGC() {
        while (true) {
            long memoryUsageBefore = getCurrentUsedMemory();
//            runtime.runFinalization();
            System.gc();
            Thread.yield();

            if (getCurrentUsedMemory() == memoryUsageBefore) {
                return;
            }
        }
    }

    private static long getCurrentUsedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static long getMemoryLeak(Runnable action) {
        runGC();
        long initSize = getCurrentUsedMemory();
        action.run();
        runGC();
        long finalSize = getCurrentUsedMemory();
        return finalSize - initSize;
    }

    public static long getMemoryLeakAverage(Runnable action, int count) {
        long totalLeak = 0;
        for (int i = 0; i < count; i++) {
            totalLeak += getMemoryLeak(action);
        }
        return totalLeak / count;
    }
}