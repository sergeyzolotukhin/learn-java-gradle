package ua.in.sz.quartz;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
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
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        Cron cron = parser.parse("0 0 12 ? * 4#1");
        ExecutionTime executionTime = ExecutionTime.forCron(cron);

        ZonedDateTime start = ZonedDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        Optional<ZonedDateTime> last = executionTime.lastExecution(start);
        log.info("{} -> {}", start, last);

//        for (int i = 0; i < 10; i++) {
//            log.info("{}", date);
//            date = executionTime.lastExecution(date.get());
//        }
    }
}
