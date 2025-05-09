package ua.in.sz.pattern.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("/file.properties")
class FilePropertyBusinessServiceTest {

    @Autowired
    public TestBusinessService messagePrinter;

    @Test
    public void test() {
        assertEquals("Hello world! - Name From Test File Property, [Description From Test File Property]", messagePrinter.hello());
    }
}