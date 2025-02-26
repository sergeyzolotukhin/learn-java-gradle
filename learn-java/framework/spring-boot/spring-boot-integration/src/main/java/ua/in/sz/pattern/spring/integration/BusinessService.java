package ua.in.sz.pattern.spring.integration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class BusinessService {
    @ServiceActivator(inputChannel = "fileChannel")
    void print(String message) {
        log.info("Message: {}", message);
    }
}
