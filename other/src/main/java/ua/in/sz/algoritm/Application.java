package ua.in.sz.algoritm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Slf4j
public class Application {
    public static List<Range<LocalTime>> merge(List<Range<LocalTime>> ranges) {
        List<Range<LocalTime>> ordered = ranges.stream()
                .sorted(Comparator.comparing(Range::getMinimum))
                .collect(Collectors.toList());

        List<Range<LocalTime>> result = new ArrayList<>();
        Stack<Range<LocalTime>> stack = new Stack<>();

        int i = 0;
        Range<LocalTime> prev = null;
        while (i < ranges.size()) {
            Range<LocalTime> curr = ranges.get(i);

            if (prev != null && !stack.empty() && prev.getMaximum().isBefore(ranges.get(i).getMinimum())) {
                curr = stack.pop();
            }

            if (prev == null) {
                i++;
                prev = curr;
            }

            if (prev.getMaximum().isAfter(curr.getMaximum())) {
                stack.push(prev);
            }

            LocalTime maximum = prev.isOverlappedBy(curr) ? curr.getMinimum() : prev.getMaximum();
            result.add(Range.between(prev.getMinimum(), maximum));

            i++;
            prev = curr;
        }


        return ranges;
    }


}
