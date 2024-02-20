package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecondWebServiceImpl implements SecondWebService {
	@Override
	public String sayHi(String text) {
		log.info("sayHi called by [{}]", getUserName());

		return "Hello second: " + text + " " + getUserName();
	}

	private String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
