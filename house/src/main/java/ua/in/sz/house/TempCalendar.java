package ua.in.sz.house;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.List;

@NoArgsConstructor(staticName = "of")
public class TempCalendar implements Iterable<TempCalendar.Month>
{
    private static final List<Month> MONTHS = ImmutableList.<Month>builder()
            .add(Month.of(-1.6, -3.8, 31)) // Jun
            .add(Month.of(-0.6, -3.2, 28)) // Feb
            .add(Month.of(5.9, 1.9, 31)) // Mar
            .add(Month.of(13.2, 7.8, 30)) // Apr
            .add(Month.of(18.5, 12.5, 31)) // May
            .add(Month.of(24.5, 18.5, 30)) // Jun
            .add(Month.of(25.8, 19.0, 31)) // Jul
            .add(Month.of(26.4, 19.3, 31)) // Aug
            .add(Month.of(21.1, 15.5, 30)) // Sep
            .add(Month.of(14.5, 10.7, 31)) // Oct
            .add(Month.of(5.5, 3.1, 30)) // Nov
            .add(Month.of(1.6, 0.4, 31)) // Dec
            .build();

    @Override
    public Iterator<Month> iterator() {
        return MONTHS.iterator();
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static final class Month {
        private double day;
        private double night;
        private int dayPerMonth;

        public double avg() {
            return (day + night) / 2.0;
        }
    }
}
