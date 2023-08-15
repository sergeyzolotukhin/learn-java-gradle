package ua.in.sz.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;

@Slf4j
public class Application {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Company.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Company company = new Company();

        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setName("Jane Doe");
        company.getEmployees().add(employee1);

        Employee employee2 = new Employee();
        employee2.setId("2");
        employee2.setName("John Smith");
        employee2.setManager(employee1);
        employee1.getReports().add(employee2);
        company.getEmployees().add(employee2);

        Employee employee3 = new Employee();
        employee3.setId("3");
        employee3.setName("Anne Jones");
        employee3.setManager(employee1);
        employee1.getReports().add(employee3);
        company.getEmployees().add(employee3);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		marshaller.marshal(company, out);

		log.info("Result: [\n{}]", out);
    }
}
