package ua.in.sz.algoritm;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Application {
    public static void main(String[] args) {
        List<ValueVO<?>> values1 = Arrays.asList(
                ValueVO.builder().start(date(0, 0)).end(date(0, 15)).value(1).build(),
                ValueVO.builder().start(date(0, 15)).end(date(0, 30)).value(2).build(),
                ValueVO.builder().start(date(0, 30)).end(date(0, 45)).value(3).build()
        );

        List<ValueVO<?>> values2 = Arrays.asList(
                ValueVO.builder().start(date(1, 0)).end(date(1, 15)).value(10).build(),
                ValueVO.builder().start(date(1, 15)).end(date(1, 30)).value(20).build(),
                ValueVO.builder().start(date(1, 30)).end(date(1, 45)).value(30).build()
        );

        List<ScheduleVO> schedules = Arrays.asList(
                ScheduleVO.builder().start(date(0, 0)).end(date(12, 0)).values(values1).build(),
                ScheduleVO.builder().start(date(1, 0)).end(date(2, 0)).values(values2).build()
        );

        log.info("{}", schedules);


    }

    private static DateTime date(int h, int m) {
        return new DateTime(2020, 1, 1, h, m);
    }

}
