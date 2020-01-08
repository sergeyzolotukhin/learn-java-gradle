package ua.in.sz.pattern.spring.camel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.FtpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@Slf4j
@SpringBootApplication
@ImportResource({"classpath:spring/ftp-servers.xml"})
public class Application implements ApplicationListener<ApplicationReadyEvent> {
    private static final String ADMIN_USER_NAME = "admin";

	@Autowired(required = false)
	@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    private List<FtpServer> ftpServers;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@SneakyThrows
	public void onApplicationEvent(ApplicationReadyEvent event) {
		for (FtpServer server : ftpServers) {
			server.start();
		}
	}
}
