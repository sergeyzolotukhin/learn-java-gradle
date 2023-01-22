package ua.in.sz.home.power;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class Main2 {
    private static final DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DD_MM_YY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final Duration PT1H = Duration.ofHours(1);

    public static void main(String[] args) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        BigDecimal value1 = BigDecimal.ZERO;
        String value2 = "";
        Duration duration = PT1H;

        Schedule schedule = Schedule.builder()
                .start(start)
                .end(end)
                .duration(duration)
                .values(d -> value1)
                .values(d -> value2)
                .values(range(start, end, duration)
                        .map(d -> value(d, duration, value1))
                        .toList())
                .values(range(start, end, duration)
                        .map(d -> value(d, duration, value2))
                        .toList())
                .build();

        print(schedule);
    }

    private static <T> Value<Object> value(LocalDateTime start, Duration duration, T value) {
        return Value.builder()
                .start(start)
                .end(start.plus(duration))
                .value(value)
                .build();
    }

    private static Stream<LocalDateTime> range(LocalDateTime start, LocalDateTime end, Duration duration) {
        Objects.requireNonNull(start, "start");
        Objects.requireNonNull(end, "end");
        Objects.requireNonNull(duration, "duration");

        return Stream.iterate(start, d -> d.isBefore(end), d -> d.plus(duration));
    }

    private static void print(Schedule schedule) {
        StringBuilder sb = new StringBuilder();
        sb.append(DD_MM_YY.format(schedule.getStart())).append(" | ");
        for (LocalDateTime date = schedule.getStart(); date.isBefore(schedule.getEnd()); date = date.plus(schedule.getDuration())) {
            sb.append(HH_MM.format(date)).append(" | ");
        }
        log.info("{}", sb);

        sb = new StringBuilder();
        sb.append("Row ").append(" | ");
        for (LocalDateTime date = schedule.getStart(); date.isBefore(schedule.getEnd()); date = date.plus(schedule.getDuration())) {
            sb.append(1).append(" | ");
        }
        log.info("{}", sb);
    }
}
