package ua.in.sz.pattern.spring.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
//        properties = { "business.service.name= Name From test" }
//        properties = {
//                "business.service.name = Name From test",
//        },
        properties = """
            business.service.name = Name From test
        """
)
class InlinePropertyBusinessServiceTest {

    @Autowired
    private AnnotationConfigApplicationContext context;
    @Autowired
    public TestBusinessService messagePrinter;

    @Test
    public void test() {
        StandardEnvironment environment = (StandardEnvironment)context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        for (PropertySource<?> propertySource : propertySources) {
            log.trace("propertySource: {}", propertySource);

            StringBuilder sb = new StringBuilder();
            if (propertySource instanceof EnumerablePropertySource<?> source) {
                for (String name : source.getPropertyNames()) {
                    sb.append(name).append("=").append(source.getProperty(name)).append(";")
                            .append("\n");
                }
            }

            log.trace("\n{}", sb);
        }

        assertEquals("Hello world! - Name From test, [Description From Property]", messagePrinter.hello());
    }
}