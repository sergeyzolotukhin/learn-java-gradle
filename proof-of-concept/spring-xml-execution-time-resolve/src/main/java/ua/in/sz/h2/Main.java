package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});

        ExecutionTimeResolver businessService = context.getBean(ExecutionTimeResolver.class, Period.ofDays(5));
        List<LocalDate> dates = businessService.resolve(Period.ofMonths(1));

        dates.forEach(d -> log.info("Date: {}", d));

        context.close();
    }
}