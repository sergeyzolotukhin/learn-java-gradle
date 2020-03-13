package ua.in.sz.camel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBean {

	public String addFirst(String message) {
		String result = String.format("first: [%s]", message);

		log.info("{}", result);

//		throw new IllegalStateException(String.format("exception: %s", message));

		return result;
	}

	public String addSecond(String message) {
		String result = String.format("second: [%s]", message);

		log.info("{}", result);

//		throw new IllegalStateException(String.format("exception: %s", message));

		return result;
	}

	public String addThird(String message) {
		String result = String.format("third: [%s]", message);

		log.info("{}", result);

//        throw new IllegalStateException(String.format("exception: %s", message));

		return result;
	}
}