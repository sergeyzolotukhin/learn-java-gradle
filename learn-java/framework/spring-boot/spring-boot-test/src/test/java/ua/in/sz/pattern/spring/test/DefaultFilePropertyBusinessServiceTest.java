package ua.in.sz.pattern.spring.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource
class DefaultFilePropertyBusinessServiceTest {

    @Autowired
    private AnnotationConfigApplicationContext context;
    @Autowired
    public TestBusinessService messagePrinter;

    @Test
    public void test() {
        StandardEnvironment environment = (StandardEnvironment)context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();


        String collect = propertySources.stream().map(PropertySource::toString)
                .collect(Collectors.joining("\n"));
        log.info("\n{}", collect);

        assertEquals("Hello world! - Name From DefaultFilePropertyBusinessServiceTest Property, [Description From DefaultFilePropertyBusinessServiceTest Property]", messagePrinter.hello());
    }
}