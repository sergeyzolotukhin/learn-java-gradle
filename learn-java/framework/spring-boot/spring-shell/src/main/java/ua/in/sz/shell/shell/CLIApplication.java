package ua.in.sz.shell.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CLIApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CLIApplication.class);
//		app.setBanner(new ApplicationBanner());
//		app.setLogStartupInfo(false);
		app.run(args);
	}

}
