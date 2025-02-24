package ua.in.sz.camel.seda;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;

@Slf4j
public class SedaRouteBuilder extends RouteBuilder {
	@Override
	public void configure() {
		from("direct:start")
				.log("${body}")
				.to("seda:r-1")
				.end();

		from("seda:r-1")
				.to("bean:seda-1?method=send")
				.end();
	}
}
