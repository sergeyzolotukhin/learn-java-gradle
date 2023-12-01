package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "application-context.xml",
                "second-application-context.xml"
        );

        BusinessService service = context.getBean(BusinessService.class);

        service.print();

        context.close();
    }

}