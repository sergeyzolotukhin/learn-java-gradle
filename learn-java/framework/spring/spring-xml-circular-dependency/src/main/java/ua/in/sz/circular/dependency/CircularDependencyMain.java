package ua.in.sz.circular.dependency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.sz.circular.dependency.service.PrintService;

@Slf4j
public class CircularDependencyMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "application-context.xml");

        PrintService secondService = context.getBean("secondService", PrintService.class);
        secondService.print();

        PrintService thirdService = context.getBean("thirdService", PrintService.class);
        thirdService.print();

        context.close();
    }
}