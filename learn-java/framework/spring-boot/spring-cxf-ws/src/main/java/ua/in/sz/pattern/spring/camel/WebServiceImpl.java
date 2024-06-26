package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class WebServiceImpl implements WebService {
	@Override
	public String sayHi(String text) {
		log.info("sayHi called by [{}]", getUserName());

		return "Hello " + text + " " + getUserName();
	}

	private String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
