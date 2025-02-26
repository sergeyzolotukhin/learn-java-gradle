package ua.in.sz.pattern.spring.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        MessageChannel inputChannel = context.getBean("fileChannel", MessageChannel.class);
        inputChannel.send(new GenericMessage<String>("World"));

        TimeUnit.SECONDS.sleep(2);
    }

    @Bean
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }
}
