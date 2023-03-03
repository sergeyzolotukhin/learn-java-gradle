package ua.in.sz.quartz;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
//@Warmup(iterations = 0)
//@Measurement(iterations = 1, time = 1)
public class BenchmarkLoop {

//    @Param({"10000000"})
//    private int N;

    private int baseYear;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkLoop.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        baseYear = baseYear();
    }

    @Benchmark
    public void loopFor(Blackhole bh) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1677882762);
        calendar.set(Calendar.MILLISECOND, 0);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int monthIndex = (year - baseYear) * 12 + month;

        bh.consume(monthIndex);
        bh.consume(day);
    }

    private int baseYear() {
//        List<String> data = new ArrayList<>();
//        for (int i = 0; i < N; i++) {
//            data.add("Number : " + i);
//        }
        return 2020;
    }

}
