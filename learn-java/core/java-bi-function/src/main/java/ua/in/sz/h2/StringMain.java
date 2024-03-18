package ua.in.sz.h2;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StringMain {
    public static final String PATH = "path";
    private static Map<String, Object> map = ImmutableBiMap.<String, Object>builder()
            .put("property_1", "value_1")
            .put("property_2", "value_2")
            .put("property_3", ImmutableMap.<String, Object>builder()
                    .put("property_1", "value_1")
                    .put("property_2", "value_2")
                    .put("property_3", ImmutableMap.<String, Object>builder()
                            .put("property_1", "value_1")
                            .put("property_2", "value_2")
                            .build())
                    .build())
            .put("property_4", "value_4")
            .build();

    public static void main(String[] args) {
        String path = String.join(".", null, "", "this", "property", "other");
        log.info("result: {}", path);

        String joined =
                Stream.of(null, "", "this", "property", "other")
                        .filter(Objects::nonNull)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.joining("."));
        log.info("result: {}", joined);

        processObject(map);

        log.info("map: {}", map);
    }


    private static <K, V> void processObject(Map<K, V> map) {
        for (Map.Entry<K, V> e : map.entrySet()) {
            processProperty(e.getKey(), e.getValue());
        }
    }

    private static <K, V> void processProperty(K property, V value) {
        String origenPath = MDC.get(PATH);
        try {
            MDC.put(PATH, join(origenPath, String.valueOf(property)));

            if (value instanceof Map<?, ?> m) {
                processObject(m);
            } else {
                log.info("process property: [{}] with value: [{}]", property, value);
            }
        } finally {
            MDC.put(PATH, origenPath);
        }
    }

    private static String join(String... values) {
        return Stream.of(values)
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("."));
    }
}
