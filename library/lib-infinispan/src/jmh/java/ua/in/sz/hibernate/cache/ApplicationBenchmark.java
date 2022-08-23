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
import java.util.List;
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
    private Cache<Object, Object> cache;

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
//        List<Object> entities = cache.values().stream().toList();
//        bh.consume(entities);

        Object o = cache.get("01");
        bh.consume(o);
    }
}
