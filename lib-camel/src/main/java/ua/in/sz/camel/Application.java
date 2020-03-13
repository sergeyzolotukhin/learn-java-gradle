package ua.in.sz.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.jndi.JndiContext;
import ua.in.sz.camel.enpoints.CompletedFtpEndpointImpl;
import ua.in.sz.camel.enpoints.FtpEndpointImpl;
import ua.in.sz.camel.enpoints.IllegalStateExceptionFtpEndpointImpl;
import ua.in.sz.camel.enpoints.RuntimeExceptionFtpEndpointImpl;

;

@Slf4j
public class Application {
	public static void main(String[] args) throws Exception {
		JndiContext jndiContext = new JndiContext();

		jndiContext.bind("ftp-1", new IllegalStateExceptionFtpEndpointImpl("ftp-1"));
		jndiContext.bind("ftp-2", new FtpEndpointImpl("ftp-2"));
		jndiContext.bind("ftp-3", new RuntimeExceptionFtpEndpointImpl("ftp-3"));
		jndiContext.bind("ftp-4", new RuntimeExceptionFtpEndpointImpl("ftp-4"));
		jndiContext.bind("completed", new CompletedFtpEndpointImpl());

		CamelContext camelContext = new DefaultCamelContext(jndiContext);
		try {
			camelContext.addRoutes(new FtpRouteBuilder());
			ProducerTemplate template = camelContext.createProducerTemplate();
			camelContext.start();

			String result = template.requestBody("direct:start", "Multicast", String.class);

			log.info("Complete successful {}",  result);
		} catch (Exception e) {
			log.error("Can't send messages", e);
		} finally {
			camelContext.stop();
		}
	}
}
