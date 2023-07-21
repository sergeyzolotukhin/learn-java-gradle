package ua.in.sz.quartz;

import com.cronutils.model.Cron;
import com.cronutils.parser.CronParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CronReplaceMain {

    @SneakyThrows
    public static void main(String[] args) {
        CronParser parser = new EventCronParser();
        Cron cron = parser.parse("0 0 12 ? * 1#4");
        log.info("{}", cron.asString());
    }
}
