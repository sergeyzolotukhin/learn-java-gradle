package ua.in.sz.camel.seda;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import ua.in.sz.camel.seda.enpoints.SedaEndpointImpl;

@Slf4j
public class SedaApplication {
	public static void main(String[] args) {
		CamelContext camelContext = new DefaultCamelContext();

		camelContext.getRegistry().bind("seda-1", new SedaEndpointImpl("seda-1"));

		try {
			camelContext.addRoutes(new SedaRouteBuilder());
			ProducerTemplate template = camelContext.createProducerTemplate();
			camelContext.start();

			String result = template.requestBody("direct:start", "My Message", String.class);

			log.info("Complete successful: {}",  result);
		} catch (Exception e) {
			log.error("Can't send messages", e);
		} finally {
			camelContext.stop();
		}
	}
}
