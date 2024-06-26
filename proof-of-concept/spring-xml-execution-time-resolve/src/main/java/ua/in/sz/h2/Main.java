package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Period;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});

        Period step = Period.ofDays(5);
        TimeUnit timeUnit = TimeUnit.DAYS;

        Optional<ExecutionTimeResolver> resolver = context.getBean(ExecutionTimeResolverFactory.class).create(step, timeUnit);
        resolver.ifPresent(r -> r.resolve(Period.ofMonths(1)).forEach(d -> log.info("Date: {}", d)));

        context.close();
    }
}