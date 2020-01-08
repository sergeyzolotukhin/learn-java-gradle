package ua.in.sz.pattern.spring.camel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.ftpserver.FtpServer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@Slf4j
@SpringBootApplication
@ImportResource({"classpath:spring/ftp-servers.xml"})
public class Application implements ApplicationListener<ApplicationReadyEvent>, BeanDefinitionRegistryPostProcessor {

	@Autowired
	private List<FtpServer> ftpServers;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		for (int i = 0; i < 3; i++) {
			GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

			beanDefinition.setBeanClass(FtpInboundRoute.class);
			beanDefinition.getPropertyValues().add("port", i);

			registry.registerBeanDefinition(String.format("route-ftp-%d", i), beanDefinition);
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	@SneakyThrows
	public void onApplicationEvent(ApplicationReadyEvent event) {
		for (FtpServer server : ListUtils.emptyIfNull(ftpServers)) {
			server.start();
		}
	}
}
