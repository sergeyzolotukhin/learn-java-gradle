package ua.in.sz.algoritm;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.*;

@Slf4j
public class Application {
    public static void main(String[] args) {
        List<ValueVO<?>> values1 = Arrays.asList(
                ValueVO.builder().start(date(0, 0)).end(date(0, 15)).value(1).build(),
                ValueVO.builder().start(date(0, 15)).end(date(0, 30)).value(2).build(),
                ValueVO.builder().start(date(0, 30)).end(date(0, 45)).value(3).build(),
                ValueVO.builder().start(date(0, 45)).end(date(1, 0)).value(4).build(),
                ValueVO.builder().start(date(1, 0)).end(date(1, 15)).value(5).build(),
                ValueVO.builder().start(date(1, 15)).end(date(1, 30)).value(6).build(),
                ValueVO.builder().start(date(1, 30)).end(date(1, 45)).value(7).build(),
                ValueVO.builder().start(date(1, 45)).end(date(2, 0)).value(7).build(),
                ValueVO.builder().start(date(2, 0)).end(date(2, 15)).value(8).build(),
                ValueVO.builder().start(date(2, 15)).end(date(2, 30)).value(9).build(),
                ValueVO.builder().start(date(2, 30)).end(date(2, 45)).value(1).build(),
                ValueVO.builder().start(date(2, 45)).end(date(3, 0)).value(2).build()
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

        // ============================================================================================================

        ScheduleVO result = ScheduleVO.builder().build();
        Map<DateTime, ValueVO<?>> valueOnDate = new LinkedHashMap<>();

        for (ScheduleVO schedule : schedules) {
            result.setStart(min(result.getStart(), schedule.getStart()));
            result.setStart(max(result.getEnd(), schedule.getEnd()));

            for (ValueVO<?> value : schedule.getValues()) {
                valueOnDate.put(value.getStart(), value);
            }
        }

        result.setValues(new ArrayList<>(valueOnDate.values()));

        log.info("Result : {}", result);
    }

    private static DateTime min(DateTime d1, DateTime d2){
        return d1 != null && d1.isBefore(d2) ? d1 : d2;
    }

    private static DateTime max(DateTime d1, DateTime d2){
        return d1 != null && d1.isAfter(d2) ? d1 : d2;
    }

    private static DateTime date(int h, int m) {
        return new DateTime(2020, 1, 1, h, m);
    }

}
