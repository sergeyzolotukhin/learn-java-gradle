package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebServiceImpl implements WebService {
	@Override
	public String sayHi(String text) {
		log.info("sayHi called");

		return "Hello " + text;
	}
}
