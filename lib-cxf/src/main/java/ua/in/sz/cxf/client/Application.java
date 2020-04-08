package ua.in.sz.cxf.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import ua.in.sz.cxf.server.WebService;

@Slf4j
public class Application {
	public static void main(String[] args) {
		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setAddress("http://localhost:8080/Hello");
		WebService client = factory.create(WebService.class);

		String response = client.sayHi("Serhij.Zolotukhin");

		log.info("Response: {}", response);
	}
}
