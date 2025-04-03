package ua.in.sz.predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandLineArgumentFilesApplication {
	public static void main(String[] args) {
		log.info("Property has value: [{}]", System.getProperty("test"));
	}
}
