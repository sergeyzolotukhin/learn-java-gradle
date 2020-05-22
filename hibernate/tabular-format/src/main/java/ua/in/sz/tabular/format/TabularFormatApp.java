package ua.in.sz.tabular.format;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
public class TabularFormatApp {
    public static void main(String[] args) {
        BigDecimal value = BigDecimal.valueOf(165465.458751146565465465464654646546544815);

        System.out.println(String.format("| %-30d | %-20s | %-20s | %20.2f |",
                0, "", "", 0.0));
        System.out.println(String.format("| %-30d | %-20s | %-20s | %20.5f |",
                1, "Serhij", "Zolotukhin", value));

        Schedule schedule = Schedule.builder()
                .numberValueList(Arrays.asList(
                                NumberScheduleValue.builder()
                                        .effectiveDay(LocalDateTime.now())
                                        .terminationDay(LocalDateTime.now().plusMinutes(15))
                                        .value(BigDecimal.valueOf(1.2))
                                        .build(),
                                NumberScheduleValue.builder()
                                        .effectiveDay(LocalDateTime.now().plusMinutes(15))
                                        .terminationDay(LocalDateTime.now().plusMinutes(30))
                                        .value(BigDecimal.valueOf(1.3))
                                        .build())).build();

        log.info("Message {}", schedule);
    }
}
