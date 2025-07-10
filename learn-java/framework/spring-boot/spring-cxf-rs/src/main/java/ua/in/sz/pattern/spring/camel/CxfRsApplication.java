package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;


@Slf4j
@SpringBootApplication
public class CxfRsApplication {
	private final Bus bus;

	public CxfRsApplication(Bus bus) {
		this.bus = bus;
	}

	@Bean
	public Server rsServer() {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setServiceBeans(List.of(new WebRsServiceImpl()));
		endpoint.setAddress("/api/");
		return endpoint.create();
	}

	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		CXFServlet cxfServlet = new CXFServlet();
		ServletRegistrationBean<CXFServlet> servletDef = new ServletRegistrationBean<>(cxfServlet, "/ws/*");
		servletDef.setLoadOnStartup(1);
		return servletDef;
	}

	public static void main(String[] args) {
		SpringApplication.run(CxfRsApplication.class, args);
	}
}
