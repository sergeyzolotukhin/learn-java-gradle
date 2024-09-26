package ua.in.sz.property.descriptor;

import lombok.SneakyThrows;
import org.slf4j.MDC;

import java.util.Optional;
import java.util.function.BiFunction;

public class LogSupport {
    @SneakyThrows
    public static void withMdcContext(String key, String value, BiFunction<String, String, String> provider, Runnable callable) {
        String oldValue = MDC.get(key);
        try {
            MDC.put(key, provider.apply(oldValue, value));

            callable.run();
        } finally {
            if (oldValue == null) {
                MDC.remove(key);
            } else {
                MDC.put(key, oldValue);
            }
        }
    }

    public static String toStringNative(Object bean) {
        return bean.getClass().getSimpleName() + "@" + System.identityHashCode(bean);
    }

    public static String concatenate(String oldValue, String newValue) {
        return Optional.ofNullable(oldValue)
                .map(o -> o + "." + newValue)
                .orElse(newValue);
    }

    public static String last(String oldValue, String newValue) {
        return newValue;
    }
}
