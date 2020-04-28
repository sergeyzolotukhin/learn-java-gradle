package ua.in.sz.algoritm;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Application {
    public static void main(String[] args) {
        List<ScheduleVO> schedules = Arrays.asList(
                ScheduleVO.builder()
                        .start(date(0, 0))
                        .end(date(12, 0))
                        .values(Arrays.asList(
                                ScheduleValueVO.builder()
                                        .start(date(0, 0))
                                        .end(date(0, 15))
                                        .value(1)
                                        .build(),
                                ScheduleValueVO.builder()
                                        .start(date(0, 15))
                                        .end(date(0, 30))
                                        .value(2)
                                        .build(),
                                ScheduleValueVO.builder()
                                        .start(date(0, 30))
                                        .end(date(0, 45))
                                        .value(3)
                                        .build()
                        ))
                        .build());

        log.info("{}", schedules);
    }

    private static DateTime date(int h, int m) {
        return new DateTime(2020, 1, 1, h, m);
    }

}
