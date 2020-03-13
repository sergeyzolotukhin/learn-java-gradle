package ua.in.sz.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.util.Objects;

import static java.util.Objects.isNull;

@Slf4j
public class FtpRouteBuilder extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		onException(IllegalStateException.class)
				.handled(true);
//				.continued(false);
				//.setHeader("status", constant("failed-1"));

		onException(RuntimeException.class)
				.handled(true);
//				.continued(false);
				//.setHeader("status", constant("failed-2"));

		from("direct:start")
				.multicast(new AggregationStrategy() {
					@Override
					public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
						if (newExchange == null) {
							return oldExchange;
						}

						if (oldExchange == null) {
							return newExchange;
						}

						Exchange answer = newExchange;

						log.info("A: {}, B: {}", oldExchange.isFailed(), newExchange.isFailed());
						if (!oldExchange.isFailed() || !newExchange.isFailed()) {
							answer.setException(null);
						}

						return answer;
					}
				})
				.to("bean:ftp-1?method=send")
				.to("bean:ftp-2?method=send")
				.to("bean:ftp-3?method=send")
				.to("bean:ftp-4?method=send")
				.end()
				.to("bean:completed?method=send");
	}
}
