package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.sz.h2.impl.DefaultExecutionTimeResolver;
import ua.in.sz.h2.impl.SingleExecutionTimeResolver;

import java.time.Period;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});

        ExecutionTimeResolver defaultExecutionTimeResolver = context.getBean(DefaultExecutionTimeResolver.class, Period.ofDays(5));
        ExecutionTimeResolver singleExecutionTimeResolver = context.getBean(SingleExecutionTimeResolver.class, Period.ofDays(5));

        log.info("Class: {}", defaultExecutionTimeResolver.getClass());
        defaultExecutionTimeResolver.resolve(Period.ofMonths(1)).forEach(d -> log.info("Date: {}", d));

        log.info("Class: {}", defaultExecutionTimeResolver.getClass());
        singleExecutionTimeResolver.resolve(Period.ofMonths(1)).forEach(d -> log.info("Date: {}", d));

        context.close();
    }
}