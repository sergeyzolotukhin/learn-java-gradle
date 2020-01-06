package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FtpInboundRoute extends RouteBuilder {
	@Override
	public void configure() {
		from("{{ftp.client}}")
				.throttle(1)
				.to("log:" + getClass().getName());
	}
}
