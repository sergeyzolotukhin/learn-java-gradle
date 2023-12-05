package ua.in.sz.logging;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
class ApplicationTest {

    @Test
    @SneakyThrows
    void main() {
        log.info("PATH: [{}]", homePath(".", "settings.gradle"));
        log.info("Test is running at [{}]", System.getProperty("user.dir"));
    }

    public static Path homePath(String start, String marker) {
        Path path = Paths.get(Paths.get(start).toUri());
        while (path != null) {
            if (path.resolve(marker).toFile().exists()) {
                return path;
            }
            path = path.getParent();
        }

        throw new IllegalStateException("Home not found");
    }


}