package ua.in.sz.quartz;

import com.cronutils.model.Cron;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ExecutionTimeTest {
    @Test
    void dayOfMonthShouldSupportDay() {
        CronParser parser = new EventCronParser();
        Cron cron = parser.parse("0 0 0 ? * 4");
        ExecutionTime executionTime = ExecutionTime.forCron(cron);

        ZonedDateTime start = ZonedDateTime.parse("2023-07-23T00:00:00Z");
        Optional<ZonedDateTime> last = executionTime.nextExecution(start);

        assertEquals(Optional.of(ZonedDateTime.parse("2023-07-26T00:00:00Z")), last);
    }
}
