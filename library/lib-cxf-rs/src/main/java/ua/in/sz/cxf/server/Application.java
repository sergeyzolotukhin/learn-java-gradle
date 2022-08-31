package ua.in.sz.cxf.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

@Slf4j
public class Application {
	@SneakyThrows
	public static void main(String[] args) {
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
		factoryBean.setResourceClasses(SimpleRepository.class);
		factoryBean.setResourceProvider(new SingletonResourceProvider(new SimpleRepository()));
		factoryBean.setProvider(new SimpleExceptionMapper());

		factoryBean.setAddress("http://localhost:8080/");
		Server server = factoryBean.create();

		log.info("Server ready...");
		Thread.sleep(60 * 1000);

		log.info("Server exiting");
		server.destroy();
		System.exit(0);
	}
}