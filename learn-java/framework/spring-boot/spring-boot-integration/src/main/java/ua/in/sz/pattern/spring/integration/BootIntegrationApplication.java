package ua.in.sz.pattern.spring.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import ua.in.sz.pattern.spring.integration.enterprise.integration.patterns.ReturningGateway;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class BootIntegrationApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(BootIntegrationApplication.class, args);

        ReturningGateway gateway = context.getBean(ReturningGateway.class);
        gateway.placeOrder("Hello World");

        TimeUnit.SECONDS.sleep(2);
    }

    @Bean
    public MessageChannel fileChannel() {
        return new PublishSubscribeChannel();
    }
}
