package ua.in.sz.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.Objects;

@Slf4j
public class AtLeastOneSuccessAggregationStrategy implements AggregationStrategy {
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			traceDelivery(newExchange);
			return newExchange;
		}

		if (newExchange == null) {
			traceDelivery(oldExchange);
			return oldExchange;
		}


		traceDelivery(newExchange);
		traceDelivery(oldExchange);

		if (!oldExchange.isFailed()) {
			newExchange.setException(null);
		}

		return newExchange;
	}

	private void traceDelivery(Exchange exchange) {
		if (exchange == null) {
			return;
		}

		log.trace("Endpoint [{}] is {} delivery",
				exchange.getProperty(Exchange.TO_ENDPOINT),
				exchange.isFailed() ? "not" : "");
	}
}
