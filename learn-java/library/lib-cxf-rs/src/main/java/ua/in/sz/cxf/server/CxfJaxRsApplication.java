package ua.in.sz.cxf.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import java.util.Arrays;

@Slf4j
public class CxfJaxRsApplication {
	@SneakyThrows
	public static void main(String[] args) {
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
		factoryBean.setResourceProvider(new SingletonResourceProvider(new SimpleRepository()));
		factoryBean.setProviders(Arrays.asList(new SimpleExceptionMapper(), new SimpleContainerRequestFilter()));

		factoryBean.setAddress("http://localhost:8080/");
		Server server = factoryBean.create();
//
		log.info("Server ready...");
		Thread.sleep(Long.MAX_VALUE);

//		log.info("Server exiting");
//		server.destroy();
//		System.exit(0);
	}
}