package ua.in.sz.quartz;

import com.cronutils.model.Cron;
import com.cronutils.parser.CronParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventCronParserTest {

    @Test
    void dayOfMonthShouldSupportDay() {
        CronParser parser = new EventCronParser();
        Cron cron = parser.parse("0 0 0 ? * 1");
        assertEquals("0 0 0 ? * 1", cron.asString());
    }

    @Test
    void dayOfMonthShouldSupportNthDay() {
        CronParser parser = new EventCronParser();
        Cron cron = parser.parse("0 0 0 ? * 1#4");
        assertEquals("0 0 0 ? * 1", cron.asString());
    }

    // ================================================================================================================
    // negative scenarios
    // ================================================================================================================

    @Test
    void dayOfMonthShouldShouldNotListOfValues() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CronParser parser = new EventCronParser();
            parser.parse("0 0 0 ? * 1,2");
        });

        assertThat(thrown.getMessage()).contains("A day=of-week does not support ','");
    }

    @Test
    void dayOfMonthShouldShouldNotListOfValuesWithHash() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CronParser parser = new EventCronParser();
            parser.parse("0 0 0 ? * 1#4,2#4");
        });

        assertThat(thrown.getMessage()).contains("A day=of-week does not support ','");
    }
}