package ua.in.sz.hibernate.cache;


import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
//@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(time = 1)
@Measurement(time = 1)
public class ApplicationBenchmark {
    private DefaultCacheManager cacheManager;
    private Cache<String, String> cache;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ApplicationBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() throws IOException {
        cacheManager = new DefaultCacheManager(Application.class.getResourceAsStream("/weatherapp-infinispan.xml"));
        cache = cacheManager.getCache("weather");

        cache.put("01", "One");
        cache.put("02", "Two");
        cache.put("13", "Two");
    }

    @TearDown
    public void tearDown() throws IOException {
        cacheManager.close();
    }

    @Benchmark
    public void findByPredicate(Blackhole bh) {
//        List<String> entities = cache.values().stream().toList();
//        bh.consume(entities); // 840 ns

//        List<Map.Entry<String, String>> entries = cache.entrySet().stream().toList();
//        bh.consume(entries); // 845 ns

//        List<CacheEntry<String, String>> entries = cache.getAdvancedCache()
//        .withFlags(Flag.CACHE_MODE_LOCAL)
//                .cacheEntrySet().stream().toList();
//        bh.consume(entries); // 846 ns

//        List<CacheEntry<String, String>> entries = cache.getAdvancedCache()
//                .withFlags(
//                        Flag.SKIP_CACHE_LOAD, Flag.SKIP_CACHE_STORE,
//                        Flag.SKIP_INDEXING, Flag.SKIP_INDEX_CLEANUP,
//                        Flag.SKIP_LOCKING, Flag.SKIP_REMOTE_LOOKUP,
//                        Flag.SKIP_STATISTICS, Flag.SKIP_LISTENER_NOTIFICATION,
//                        Flag.SKIP_OWNERSHIP_CHECK,
//                        Flag.SKIP_SIZE_OPTIMIZATION,
//                        Flag.SKIP_XSITE_BACKUP)
//                .cacheEntrySet().stream()
//                .filter(CacheFilters.predicate((key, value, metadata) -> key.startsWith("0")))
//                .toList();
//        bh.consume(entries); // 1042 ns

//        for (Map.Entry<String, String> e : cache.entrySet()) { // 347 ns
//            if (e == null) {
//                throw new IllegalStateException();
//            }
//        }

        for (String e : cache.keySet()) { // 313 ns
            if (e == null) {
                throw new IllegalStateException();
            }
        }

//        Object o = cache.get("01"); // 54 ns
//        bh.consume(o);
    }
}
