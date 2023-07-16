package ua.in.sz.quartz;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class CronMain {
    public static void main(String[] args) throws Exception {
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
        Cron cron = parser.parse("0 0 12 * * ?");
        ExecutionTime executionTime = ExecutionTime.forCron(cron);

        Date startDate = new Date();
        log.info("{}", startDate);

        ZonedDateTime dateTime = ZonedDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        Optional<ZonedDateTime> date = executionTime.lastExecution(dateTime);
        for (int i = 0; i < 10; i++) {
            log.info("{}", date);
            date = executionTime.lastExecution(date.get());
        }
    }
}
