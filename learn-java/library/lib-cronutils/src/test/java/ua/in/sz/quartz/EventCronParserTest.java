package ua.in.sz.quartz;

import com.cronutils.model.Cron;
import com.cronutils.parser.CronParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventCronParserTest {

    @Test
    void parseWithNthDay() {
        CronParser parser = new EventCronParser();
        Cron cron = parser.parse("0 0 12 ? * 1#4");
        assertEquals("0 0 12 ? * 1", cron.asString());
    }

    @Test
    void parseSeveralWithNthDay() {
        CronParser parser = new EventCronParser();
        Cron cron = parser.parse("0 0 12 ? * 1#4,2#4");
        assertEquals("0 0 12 ? * 1,2", cron.asString());
    }

    @Test
    void parseWithoutNthDay() {
        CronParser parser = new EventCronParser();
        Cron cron = parser.parse("0 0 12 ? * 1");
        assertEquals("0 0 12 ? * 1", cron.asString());
    }

    @Test
    void parseSeveralWithoutNthDay() {
        CronParser parser = new EventCronParser();
        Cron cron = parser.parse("0 0 12 ? * 1,2");
        assertEquals("0 0 12 ? * 1,2", cron.asString());
    }
}