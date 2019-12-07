package ua.in.sz.notcomplited.loadproperty;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Application {
	public static void main(String[] args) throws IOException {
		log.info("start execution");

		System.getProperties().load(Application.class.getResourceAsStream("/application.properties"));

		log.info("Author: {}", System.getProperty("author"));


		log.info("end execution");
	}
}
