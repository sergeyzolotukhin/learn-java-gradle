package ua.in.sz.cxf.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import ua.in.sz.cxf.server.WebService;

import java.util.ResourceBundle;

@Slf4j
public class Application {
	public static final String URL = "url";
	public static final String PROPERTIES = "application";

	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES);

		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setAddress(rb.getString(URL));
		WebService client = factory.create(WebService.class);

		String response = client.sayHi("Serhij.Zolotukhin");

		log.info("Response: {}", response);
	}
}
