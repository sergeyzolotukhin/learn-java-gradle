package ua.in.sz.camel.enpoints;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompletedFtpEndpointImpl implements FtpEndpoint {
	@Override
	public String send(String message) {
		log.info("Completed: {}", message);

		return "Completed";
	}
}