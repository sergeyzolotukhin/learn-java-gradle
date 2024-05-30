package ua.in.sz.junit5;

import org.instancio.Instancio;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;

@State(Scope.Thread)
public class JMHSumBigDecimal {
    public static final int NUMBER_OF_VALUE = 100;

    BigDecimal[] a;
    BigDecimal[] b;

    BigDecimal[] r;

    @Setup
    public void setup() {
        a = Instancio.stream(BigDecimal.class)
                .limit(NUMBER_OF_VALUE)
                .toArray(BigDecimal[]::new);

        b = Instancio.stream(BigDecimal.class)
                .limit(NUMBER_OF_VALUE)
                .toArray(BigDecimal[]::new);

        r = new BigDecimal[NUMBER_OF_VALUE];
    }

    @TearDown
    public void tearDown() {
        a = null;
        b = null;
        r = null;
    }

    @Benchmark
    public void add() {
        for (int i = 0; i < NUMBER_OF_VALUE; i++) {
            r[i] = a[i].add(b[i]);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSumBigDecimal.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                .build();

        new Runner(opt).run();
    }
}
