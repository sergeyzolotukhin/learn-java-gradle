package ua.in.sz.algoritm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;

import java.time.LocalTime;
import java.util.*;

@Slf4j
public class Application {
    public static void main(String[] args) {
        List<Range<LocalTime>> ranges = Arrays.asList(
                range(0, 12),
                range(6, 7),
                range(1, 3),

                range(1, 2),
                range(4, 11),
                range(9, 10)
        );

        log.info("{}", ranges);

        TreeSet<Range<LocalTime>> tree = new TreeSet<>(Comparator.comparing(Range::getMinimum));

        tree.addAll(ranges);

        List<Range<LocalTime>> collect = new ArrayList<>(tree);

        log.info("{}", collect);

        Range<LocalTime> ceiling = tree.ceiling(range(3, 4));
        log.info("{}", ceiling);

        Range<LocalTime> first = tree.first();
        log.info("{}", first);
    }

    private static Range<LocalTime> range(int sH, int eH) {
        return Range.between(LocalTime.of(sH, 0), LocalTime.of(eH, 0));
    }

}
