package ua.in.sz.junit5.resources;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ReadDataTest {
    @Test
    void main_1() throws IOException {
        String path = "src/test/data/data.properties";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        log.info("main 1 {}", absolutePath);

        Properties prop = new Properties();
        prop.load(new FileInputStream(file));

        log.info("main 2 {}", prop);
    }
}
