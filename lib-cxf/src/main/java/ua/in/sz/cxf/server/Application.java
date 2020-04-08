package ua.in.sz.cxf.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.frontend.ServerFactoryBean;

import java.util.ResourceBundle;

import static ua.in.sz.cxf.Constants.PROPERTIES;
import static ua.in.sz.cxf.Constants.URL;

@Slf4j
public class Application {
	@SneakyThrows
	public static void main(String[] args) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES);

		ServerFactoryBean serverFactory = new ServerFactoryBean();
		serverFactory.setServiceClass(WebService.class);
		serverFactory.setAddress(resourceBundle.getString(URL));
		serverFactory.setServiceBean(new WebServiceImpl());
		serverFactory.create();

		log.info("Server started");
	}
}