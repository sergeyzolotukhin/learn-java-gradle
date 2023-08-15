package ua.in.sz.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;

@Slf4j
public class Application {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Employee o = new Employee();
        o.setId(1);
        o.setName("Banana");
        o.setSalary(1.125f);
        Description d = new Description();
        d.setId(1);
        d.setName("Description 1");
        o.setDescription(d);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		marshaller.marshal(o, out);

		log.info("Result: [\n{}]", out);
    }
}
