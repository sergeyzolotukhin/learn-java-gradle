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

/*
https://medium.com/codex/method-inlining-in-java-84caec9b3e18
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
//@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G", "-XX:+Inline", "-XX:MaxInlineSize=50", "-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining"})
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G", "-XX:+Inline", "-XX:MaxInlineSize=50"})
//@Warmup(iterations = 0)
//@Measurement(iterations = 1, time = 1)
public class BenchmarkLoop {

//    @Param({"10000000"})
//    private int N;

    private int baseYear;
    private int [] holidays;

    private long baseMillis;

    public static void main(String[] args) throws RunnerException {
//        BenchmarkLoop b = new BenchmarkLoop();
//        b.setup();
//        b.isHolidayByDayNo(1672531200000L);

        Options opt = new OptionsBuilder()
                .include(BenchmarkLoop.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        baseYear = 2020;
        holidays = new int[10 * 12];
        baseMillis = 1577836800000L;  // 1 January 2020 г., 0:00:00
    }

//    @Benchmark
    public void holidayByCalendar(Blackhole bh) {
        long millis = 1677882762000L;

        boolean holiday = isHoliday(millis);

        bh.consume(holiday);
    }

//    @Benchmark
    public void holidayByDayNo(Blackhole bh) {
        long millis = 1677882762000L;

        boolean holiday = isHolidayByDayNo(millis);

        bh.consume(holiday);
    }

    @Benchmark
    public void holidayByDayNoWithoutDivision(Blackhole bh) {
        long millis = 1677882762000L;

        boolean holiday = isHolidayByDayNoWithoutDivision(millis);

        bh.consume(holiday);
    }

    private boolean isHolidayByDayNo(long millis) {
        int day = (int)((millis - baseMillis) / 86400000);
        int index = day / 32;
        int offset = day % 32;
        return ((0x1 << offset) & holidays[index]) > 0;
    }

    private boolean isHolidayByDayNoWithoutDivision(long millis) {
        int day = (int)((millis - baseMillis) / 86400000);
        int index = day >> 5;
        int offset = day & 0b11111;
        return ((0x1 << offset) & holidays[index]) > 0;
    }

    private boolean isHoliday(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.set(Calendar.MILLISECOND, 0);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int monthIndex = (year - baseYear) * 12 + month;
        return ((0x1 << day) & holidays[monthIndex]) > 0;
    }

    /*
Benchmark                        Mode  Cnt    Score   Error  Units
BenchmarkLoop.holidayByCalendar  avgt    5  129.009 � 1.732  ns/op
BenchmarkLoop.holidayByDayNo     avgt    5    1.083 � 0.110  ns/op
BenchmarkLoop.holidayByDayNoWith avgt    5    0.820 � 0.013  ns/op

BenchmarkLoop.holidayByCalendar  thrpt    5      7 629 421.714 �   194980.219  ops/s
BenchmarkLoop.holidayByDayNo     thrpt    5    929 667 622.308 � 84336041.667  ops/s
BenchmarkLoop.holidayByDayNoWith thrpt    5  1 195 616 977.168 � 41995966.847  ops/s
     */


}
