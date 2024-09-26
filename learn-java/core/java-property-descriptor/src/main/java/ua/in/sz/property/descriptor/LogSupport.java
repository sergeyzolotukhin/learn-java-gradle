package ua.in.sz.property.descriptor;

import lombok.SneakyThrows;
import org.slf4j.MDC;

import java.util.Optional;

public class LogSupport {
    @SneakyThrows
    public static void withMdcContext(String key, String value, Runnable callable) {
        String oldValue = MDC.get(key);
        try {
            String newValue = Optional.ofNullable(oldValue)
                    .map(o -> o + "." + value)
                    .orElse(value);

            MDC.put(key, newValue);

            callable.run();
        } finally {
            if (oldValue == null) {
                MDC.remove(key);
            } else {
                MDC.put(key, oldValue);
            }
        }
    }
}
