package ua.in.sz.cxf.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.frontend.ServerFactoryBean;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Application {
	@SneakyThrows
	public static void main(String[] args) {
		ServerFactoryBean serverFactory = new ServerFactoryBean();
		serverFactory.setServiceClass(WebService.class);
		serverFactory.setAddress("http://localhost:8080/Hello");
		serverFactory.setServiceBean(new WebServiceImpl());
		serverFactory.create();

		TimeUnit.MINUTES.sleep(1);

		log.info("Stop");
	}
}