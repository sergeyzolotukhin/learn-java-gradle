package ua.in.sz.togglz;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Application {
	public static void main(String[] args) {
		if (MyFeatures.ONE.isActive()) {
			log.info("Feature one is enable");
		}

		if (MyFeatures.TWO.isActive()) {
			log.info("Feature two is enable");
		}

		log.info("Application is executed");
	}
}
