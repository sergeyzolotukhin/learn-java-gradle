package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		String classpath = System.getProperty("java.class.path");
		String[] classpathEntries = classpath.split(File.pathSeparator);

		for (String classpathEntry : classpathEntries) {
			log.trace("path: [{}]", classpathEntry);
		}

		// Just a comment
		log.trace("Start application");
		SpringApplication.run(Application.class, args);
	}
}