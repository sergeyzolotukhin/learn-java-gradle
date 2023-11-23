package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Slf4j
@SuppressWarnings("unchecked")
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"}, false);
        context.refresh();

        List<String> names = (List<String>) context.getBean("names", List.class);
        names.add("Name Serhij");
        names.add("Name Serhij 2");

        BusinessService businessService = context.getBean(BusinessService.class);
        businessService.print();

        context.close();
    }
}