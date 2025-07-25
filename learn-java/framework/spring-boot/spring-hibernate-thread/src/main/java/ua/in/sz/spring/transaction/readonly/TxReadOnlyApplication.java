package ua.in.sz.spring.transaction.readonly;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class TxReadOnlyApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TxReadOnlyApplication.class, args);

		log.info("\n\n");
		for (String beanDefinitionName : context.getBeanDefinitionNames()) {
			log.info("\t{} -> {}", beanDefinitionName , context.getBean(beanDefinitionName).getClass().getName());
		}
		log.info("\n\n");

		context.close();
	}
}