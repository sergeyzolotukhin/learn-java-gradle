package ua.in.sz.pattern.spring.yaml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import ua.in.sz.pattern.spring.property.BusinessService;

// https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/boot-features-external-config.html
// https://www.baeldung.com/spring-yaml-propertysource

@Slf4j
@Configuration
@SpringBootApplication
@EnableConfigurationProperties(AcmeProperties.class)
@PropertySources({
        @PropertySource(value = "classpath:rules.yml", factory = YamlPropertySourceFactory.class)
})
public class YamlApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(YamlApplication.class, args);

        YamlBusinessService bean = context.getBean(YamlBusinessService.class);
        bean.print();
    }

    @Bean
    public YamlBusinessService yamlBusinessService(AcmeProperties properties) {
        log.info("YAML Business Service: {}", properties.toString());
        return new YamlBusinessService();
    }
}
