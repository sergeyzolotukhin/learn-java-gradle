package ua.in.sz.h2;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface ExecutionTimeResolver {
    List<LocalDate> resolve(Period period);
}
