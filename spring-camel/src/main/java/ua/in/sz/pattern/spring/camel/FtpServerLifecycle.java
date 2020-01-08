package ua.in.sz.pattern.spring.camel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.FtpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FtpServerLifecycle implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	private List<FtpServer> ftpServers;

	@Override
	@SneakyThrows
	public void onApplicationEvent(ApplicationReadyEvent event) {
		for (FtpServer server : ftpServers) {
			server.start();
		}
	}
}
