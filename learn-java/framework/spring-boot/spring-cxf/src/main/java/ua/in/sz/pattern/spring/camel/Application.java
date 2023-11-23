package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
