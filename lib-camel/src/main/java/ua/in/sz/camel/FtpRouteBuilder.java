package ua.in.sz.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;

@Slf4j
public class FtpRouteBuilder extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("direct:start")
				.multicast()
				.to("bean:ftp-1?method=send")
				.to("bean:ftp-2?method=send")
				.to("bean:ftp-3?method=send")
				.end();
	}
}
