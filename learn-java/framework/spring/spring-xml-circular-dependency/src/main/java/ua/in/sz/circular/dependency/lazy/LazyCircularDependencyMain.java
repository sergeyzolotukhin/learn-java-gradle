package ua.in.sz.circular.dependency.lazy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.sz.circular.dependency.lazy.service.LazyPrintService;

@Slf4j
public class LazyCircularDependencyMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "lazy/application-context.xml");

        context.getBean("firstService", LazyPrintService.class).print();
        context.getBean("secondService", LazyPrintService.class).print();

        context.close();
    }
}