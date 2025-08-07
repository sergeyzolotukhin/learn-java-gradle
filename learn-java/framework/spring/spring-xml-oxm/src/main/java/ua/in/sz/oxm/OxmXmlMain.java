package ua.in.sz.oxm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;

import javax.xml.transform.stream.StreamResult;

import ua.in.sz.oxm.model.Student;

import java.io.StringWriter;

@Slf4j
public class OxmXmlMain {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"application-context.xml"},
                false);
        context.refresh();

        Marshaller marshaller = (Marshaller) context.getBean("jaxbMarshaller");

        Student student = new Student();
        student.setAge(14);
        student.setName("Soniya");

        StringWriter sw = new StringWriter();
        marshaller.marshal(student, new StreamResult(sw));
        log.info("xml: {}", sw);

        context.close();
    }
}