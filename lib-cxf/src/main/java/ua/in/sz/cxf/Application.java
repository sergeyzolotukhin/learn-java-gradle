package ua.in.sz.cxf;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.frontend.ServerFactoryBean;
import ua.in.sz.cxf.impl.WebService;
import ua.in.sz.cxf.impl.WebServiceImpl;

@Slf4j
public class Application {
	@SneakyThrows
	public static void main(String[] args) {
		WebServiceImpl helloworldImpl = new WebServiceImpl();
		ServerFactoryBean svrFactory = new ServerFactoryBean();
		svrFactory.setServiceClass(WebService.class);
		svrFactory.setAddress("http://localhost:9000/Hello");
		svrFactory.setServiceBean(helloworldImpl);
//		svrFactory.setFeatures(Collections.singletonList(new LoggingFeature()));
		//svrFactory.getServiceFactory().setDataBinding(new AegisDatabinding());
		svrFactory.create();

		Thread.sleep(5 * 60 * 1000);
		System.out.println("Server exiting");
		System.exit(0);
	}
}