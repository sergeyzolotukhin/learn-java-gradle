package ua.in.sz.pattern.spring.test;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class TestBusinessService {
    @Value("${business.service.name:Name from default}")
    private String name;
    @Value("${business.service.description:Description from default}")
    private String description;

    String hello() {
        return "Hello world! - " + name + ", [" + description + "]";
    }
}
