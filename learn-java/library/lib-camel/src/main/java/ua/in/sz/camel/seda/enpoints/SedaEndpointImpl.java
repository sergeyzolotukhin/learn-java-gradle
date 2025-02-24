package ua.in.sz.camel.seda.enpoints;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SedaEndpointImpl implements SedaEndpoint {
	private final String name;

	public SedaEndpointImpl(String name) {
		this.name = name;
	}

	@Override
	public String send(String message) {
		String result = String.format("%s: [%s]", name, message);

		log.info("{}", result);

		return result;
	}
}