package ua.in.sz.business.days;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

/**
 * <a href="https://github.com/OpenGamma/Strata/blob/main/modules/basics/src/main/java/com/opengamma/strata/basics/date/ImmutableHolidayCalendar.java">Source code</a>
 * <a href="https://github.com/yusuke/businessCalendar4J">Business Calendar library for Java</a>
 */
public class ImmutableHolidayCalendar {
    private int startYear;
    private int[] lookup;

    // ================================================================================================================
    //
    // ================================================================================================================

    public LocalDate shift(LocalDate date, int amount) {
        try {
            if (amount > 0) {
                // day-of-month: minus one for zero-based day-of-month, plus one to start from next day
                return shiftNext(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), amount);
            } else if (amount < 0) {
                // day-of-month: minus one to start from previous day
                return shiftPrev(date.getYear(), date.getMonthValue(), date.getDayOfMonth() - 1, amount);
            }
            return date;

        } catch (ArrayIndexOutOfBoundsException ex) {
            return shiftOutOfRange(date, amount);
        }
    }

    private LocalDate shiftNext(int baseYear, int baseMonth, int baseDom0, int amount) {
        // find data for month
        int index = (baseYear - startYear) * 12 + baseMonth - 1;
        int monthData = lookup[index];
        // loop around amount, the number of days to shift by
        // use domOffset to keep track of day-of-month
        int domOffset = baseDom0;
        for (int amt = amount; amt > 0; amt--) {
            // shift to move the target day-of-month into bit-0, removing earlier days
            int shifted = monthData >>> domOffset;
            // recurse to next month if no more business days in the month
            if (shifted == 0) {
                return baseMonth == 12 ? shiftNext(baseYear + 1, 1, 0, amt) : shiftNext(baseYear, baseMonth + 1, 0, amt);
            }
            // find least significant bit, which is next business day
            // use JDK numberOfTrailingZeros() method which is mapped to a fast intrinsic
            domOffset += (Integer.numberOfTrailingZeros(shifted) + 1);
        }
        return LocalDate.of(baseYear, baseMonth, domOffset);
    }

    private LocalDate shiftPrev(int baseYear, int baseMonth, int baseDom, int amount) {
        // find data for month
        int index = (baseYear - startYear) * 12 + baseMonth - 1;
        int monthData = lookup[index];
        // loop around amount, the number of days to shift by
        // use domOffset to keep track of day-of-month
        int domOffset = baseDom;
        for (int amt = amount; amt < 0; amt++) {
            // shift to move the target day-of-month into bit-31, removing later days
            int shifted = (monthData << (32 - domOffset));
            // recurse to previous month if no more business days in the month
            if (shifted == 0 || domOffset <= 0) {
                return baseMonth == 1 ? shiftPrev(baseYear - 1, 12, 31, amt) : shiftPrev(baseYear, baseMonth - 1, 31, amt);
            }
            // find most significant bit, which is previous business day
            // use JDK numberOfLeadingZeros() method which is mapped to a fast intrinsic
            domOffset -= (Integer.numberOfLeadingZeros(shifted) + 1);
        }
        return LocalDate.of(baseYear, baseMonth, domOffset + 1);
    }

    private LocalDate shiftOutOfRange(LocalDate date, int amount) {
//        if (date.getYear() >= 0 && date.getYear() < 10000) {
//            return HolidayCalendar.super.shift(date, amount);
//        }
        throw new IllegalArgumentException("Date is outside the accepted range (year 0000 to 10,000): " + date);
    }

    // ================================================================================================================
    //
    // ================================================================================================================

    static ImmutableHolidayCalendar of(
            HolidayCalendarId id,
            SortedSet<LocalDate> holidays,
            Set<DayOfWeek> weekendDays,
            Iterable<LocalDate> workingDays) {

//        ArgChecker.notNull(id, "id");
        int weekends = weekendDays.stream().mapToInt(dow -> 1 << (dow.getValue() - 1)).sum();
        // initial case where no holiday dates are specified
        int startYear = 0;
        int[] lookup = new int[0];
        if (!holidays.isEmpty()) {
            // normal case where holidays are specified
            startYear = holidays.first().getYear();
            int endYearExclusive = holidays.last().getYear() + 1;
            lookup = buildLookupArray(holidays, weekendDays, startYear, endYearExclusive, workingDays);
        }
        return new ImmutableHolidayCalendar(id, weekends, startYear, lookup, false);
    }

    ImmutableHolidayCalendar(HolidayCalendarId id, int weekendDays, int startYear, int[] lookup, boolean flag) {
//        this.id = ArgChecker.notNull(id, "id");
//        this.weekends = weekendDays;
        this.startYear = startYear;
        this.lookup = lookup;
    }

    // create and populate the int[] lookup
    // use 1 for business days and 0 for holidays
    private static int[] buildLookupArray(
            Iterable<LocalDate> holidays,
            Iterable<DayOfWeek> weekendDays,
            int startYear,
            int endYearExclusive,
            Iterable<LocalDate> workingDays) {

        // array that has one entry for each month
        int[] array = new int[(endYearExclusive - startYear) * 12];
        // loop through all months to handle end-of-month and weekends
        LocalDate firstOfMonth = LocalDate.of(startYear, 1, 1);
        for (int i = 0; i < array.length; i++) {
            int monthLen = firstOfMonth.lengthOfMonth();
            // set each valid day-of-month to be a business day
            // the bits for days beyond the end-of-month will be unset and thus treated as non-business days
            // the minus one part converts a single set bit into each lower bit being set
            array[i] = (1 << monthLen) - 1;
            // unset the bits associated with a weekend
            // can unset across whole month using repeating pattern of 7 bits
            // just need to find the offset between the weekend and the day-of-week of the 1st of the month
            for (DayOfWeek weekendDow : weekendDays) {
                int daysDiff = weekendDow.getValue() - firstOfMonth.getDayOfWeek().getValue();
                int offset = (daysDiff < 0 ? daysDiff + 7 : daysDiff);
                array[i] &= ~(0b10000001000000100000010000001 << offset); // CSIGNORE
            }
            firstOfMonth = firstOfMonth.plusMonths(1);
        }
        // unset the bit associated with each holiday date
        for (LocalDate date : holidays) {
            int index = (date.getYear() - startYear) * 12 + date.getMonthValue() - 1;
            array[index] &= ~(1 << (date.getDayOfMonth() - 1));
        }
        // set the bit associated with each overriding working day
        for (LocalDate date : workingDays) {
            if (date.getYear() < startYear || date.getYear() >= endYearExclusive) {
                continue;
            }
            int index = (date.getYear() - startYear) * 12 + date.getMonthValue() - 1;
            array[index] |= (1 << (date.getDayOfMonth() - 1));
        }
        return array;
    }

    private static class HolidayCalendarId {

    }
}
