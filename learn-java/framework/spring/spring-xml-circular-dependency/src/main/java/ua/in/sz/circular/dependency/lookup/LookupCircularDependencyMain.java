package ua.in.sz.circular.dependency.lookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.sz.circular.dependency.lookup.service.PrintService;

@Slf4j
public class LookupCircularDependencyMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "lookup/application-context.xml");

        context.getBean("secondService", PrintService.class).print();
        context.getBean("thirdService", PrintService.class).print();

        context.close();
    }
}