package ua.in.sz.cxf.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import ua.in.sz.cxf.server.WebService;

import java.util.ResourceBundle;

import static ua.in.sz.cxf.Constants.PROPERTIES;
import static ua.in.sz.cxf.Constants.URL;

@Slf4j
public class Application {
	public static void main(String[] args) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES);

		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setAddress(resourceBundle.getString(URL));
		WebService client = factory.create(WebService.class);

		String response = client.sayHi("Serhij.Zolotukhin");

		log.info("Response: {}", response);
	}
}
