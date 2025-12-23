package ua.in.sz.undertow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.ErgoUndertowServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class UndertowApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UndertowApplication.class, args);

        log.info("Hello world!");
//        context.close();
    }

    @Bean
    ErgoUndertowServletWebServerFactory undertowServletWebServerFactory(
            ObjectProvider<UndertowDeploymentInfoCustomizer> deploymentInfoCustomizers,
            ObjectProvider<UndertowBuilderCustomizer> builderCustomizers) {
        ErgoUndertowServletWebServerFactory factory = new ErgoUndertowServletWebServerFactory();
        factory.getDeploymentInfoCustomizers().addAll(deploymentInfoCustomizers.orderedStream().toList());
        factory.getBuilderCustomizers().addAll(builderCustomizers.orderedStream().toList());
        return factory;
    }
}