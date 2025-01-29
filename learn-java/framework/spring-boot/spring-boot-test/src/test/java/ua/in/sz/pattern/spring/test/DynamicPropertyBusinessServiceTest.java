package ua.in.sz.pattern.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DynamicPropertyBusinessServiceTest {

    @Autowired
    public TestBusinessService messagePrinter;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("business.service.name", () -> "Name From runtime");
    }

    @Test
    public void test() {
        assertEquals("Hello world! - Name From runtime, [Description From Property]", messagePrinter.hello());
    }
}