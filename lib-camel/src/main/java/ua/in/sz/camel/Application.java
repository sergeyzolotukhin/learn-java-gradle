package ua.in.sz.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;
import org.apache.camel.support.jndi.JndiContext;

import java.util.List;
import java.util.Objects;

;

@Slf4j
public class Application {
	public static void main(String[] args) throws Exception {
		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("myBean", new MyBean());
		CamelContext camelContext = new DefaultCamelContext(jndiContext);
		try {
			camelContext.addRoutes(new RouteBuilder() {
				public void configure() {
					from("direct:start")
							.multicast(new GroupedExchangeAggregationStrategy())
							.to("bean:myBean?method=addFirst", "bean:myBean?method=addSecond", "bean:myBean?method=addThird")
							.end();
				}
			});
			ProducerTemplate template = camelContext.createProducerTemplate();
			camelContext.start();

			List<Exchange> exchanges = template.requestBody("direct:start", "Multicast", List.class);

			log.info("Complete successful {}", isSuccessful(exchanges));
		} catch (Exception e) {
			log.error("Can't send messages", e);
		} finally {
			camelContext.stop();
		}
	}

	private static boolean isSuccessful(List<Exchange> exchanges) {
		int exchangeCount = exchanges.size();
		long failedCount = exchanges.stream()
				.map(Exchange::getException)
				.filter(Objects::nonNull)
				.count();

		boolean successful = exchangeCount > failedCount;

		log.info("Complete successful {}. failed {} from {}",
				successful, failedCount, exchangeCount);

		return successful;
	}
}
