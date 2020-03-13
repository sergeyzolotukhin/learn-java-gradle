package ua.in.sz.camel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FtpEndpointImpl implements FtpEndpoint {
	private final String name;

	public FtpEndpointImpl(String name) {
		this.name = name;
	}

	@Override
	public String send(String message) {
		String result = String.format("%s: [%s]", name, message);

		log.info("{}", result);

//		throw new IllegalStateException(String.format("exception: %s", message));

		return result;
	}
}