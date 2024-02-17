package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import jakarta.xml.ws.Endpoint;

@Slf4j
@SpringBootApplication
public class Application  {
	private final Bus bus;

	public Application(Bus bus) {
		this.bus = bus;
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new WebServiceImpl());
		endpoint.publish("/Hello");
		return endpoint;
	}

	@Bean
	public Endpoint secondEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new SecondWebServiceImpl());
		endpoint.publish("/second/Hello");
		return endpoint;
	}

	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		CXFServlet cxfServlet = new CXFServlet();
		ServletRegistrationBean<CXFServlet> servletDef = new ServletRegistrationBean<>(cxfServlet, "/ws/*");
		servletDef.setLoadOnStartup(1);
		return servletDef;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
