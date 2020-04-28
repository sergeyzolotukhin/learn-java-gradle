package ua.in.sz.algoritm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ApplicationTest {

    @Test
    void merge() {
        List<Range<LocalTime>> ranges = Arrays.asList(
                range(0, 12),
                range(1, 2),
                range(1, 3),
                range(4, 11),
                range(6, 7),
                range(9, 10)
        );

        List<Range<LocalTime>> merge = Application.merge(ranges);

        log.info("{}", merge);
        Assertions.assertEquals(
                "[[00:00..12:00], [01:00..02:00], [01:00..03:00], [04:00..11:00], [06:00..07:00], [09:00..10:00]]",
                String.format("%s", merge));
    }

    private static Range<LocalTime> range(int sH, int eH) {
        return Range.between(LocalTime.of(sH, 0), LocalTime.of(eH, 0));
    }

}