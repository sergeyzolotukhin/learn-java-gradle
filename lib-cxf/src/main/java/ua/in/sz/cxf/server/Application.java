package ua.in.sz.cxf.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.frontend.ServerFactoryBean;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Application {
	public static final String URL = "url";
	public static final String PROPERTIES = "application";

	@SneakyThrows
	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES);

		ServerFactoryBean serverFactory = new ServerFactoryBean();
		serverFactory.setServiceClass(WebService.class);
		serverFactory.setAddress(rb.getString(URL));
		serverFactory.setServiceBean(new WebServiceImpl());
		serverFactory.create();

		TimeUnit.MINUTES.sleep(1);

		log.info("Stop");
	}
}