package ua.in.sz.pattern.spring.camel;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Setter
public class FtpInboundRoute extends RouteBuilder {
	private int port;

	@Value("${ftp.client}")
	private String configUri;

	@Override
	public void configure() {
		String uri = String.format(configUri, 21 + port);

		from(uri)
				.throttle(1)
				.to("log:" + getClass().getName());
	}
}
