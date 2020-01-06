package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.impl.PropertiesUserManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@Slf4j
@SpringBootApplication
public class Application {
    private static final String ADMIN_USER_NAME = "admin";

    @Value("classpath:users.properties")
    private File usersFile;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SpringFtpServer ftpServer() {
		FtpServerFactory factory = new FtpServerFactory();
		factory.setUserManager(ftpUserManager());
		FtpServer server = factory.createServer();

		return new SpringFtpServer(server);
	}

    private PropertiesUserManager ftpUserManager() {
        return new PropertiesUserManager(new ClearTextPasswordEncryptor(), usersFile, ADMIN_USER_NAME);
    }
}
