package ua.in.sz.junit5.resources;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.io.Resource;
import org.junit.platform.commons.support.ResourceSupport;

@Slf4j
class NativeResourceTest {
    @Test
    @SneakyThrows
    void weCanAccessToFile() {
        ResourceSupport.tryToGetResources("resource.txt")
                .get()
                .forEach(r -> {
                    log.info("\n\t[{}]\n\t[{}]\n\t[{}]", toString(r), r.getName(), r.getUri());
                });
    }

    @SneakyThrows
    private static String toString(Resource r) {
        return new String(r.getInputStream().readAllBytes());
    }
}