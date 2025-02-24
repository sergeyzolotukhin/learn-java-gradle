package ua.in.sz.camel.aggregation.strategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

@Slf4j
public class FtpRouteBuilder extends RouteBuilder {
	@Override
	public void configure() {
		onException(IllegalStateException.class)
				.process(exchange -> log.warn("illegal {}", exchange.getProperty(Exchange.TO_ENDPOINT)))
				.logExhausted(false);

		onException(RuntimeException.class)
				.process(exchange -> log.warn("runtime {}", exchange.getProperty(Exchange.TO_ENDPOINT)))
				.logExhausted(false);

		from("direct:start")
				.multicast(new AtLeastOneSuccessAggregationStrategy())
				.to("bean:ftp-1?method=send")
				.to("bean:ftp-2?method=send")
				.to("bean:ftp-3?method=send")
				.to("bean:ftp-4?method=send")
				.end();
	}
}
