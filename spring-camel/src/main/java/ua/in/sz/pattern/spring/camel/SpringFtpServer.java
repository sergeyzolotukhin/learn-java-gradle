package ua.in.sz.pattern.spring.camel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.FtpServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class SpringFtpServer {
	private final FtpServer delegate;

	public SpringFtpServer(FtpServer delegate) {
		this.delegate = delegate;
	}

	@PostConstruct
	@SneakyThrows
	public void start() {
		delegate.start();
	}

	@PreDestroy
	@SneakyThrows
	public void stop() {
		delegate.stop();
	}
}