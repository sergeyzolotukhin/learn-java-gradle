package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        BusinessService businessService = context.getBean(BusinessService.class);
        businessService.print();

        context.close();
        log.info("Hello world!");
    }
}