package ua.in.sz.property.descriptor;

import lombok.SneakyThrows;
import org.slf4j.MDC;

import java.util.Optional;
import java.util.function.BiFunction;

public class LogSupport {

    public static void withMdcContext(String key, Object value, Runnable callable) {
        withMdcContext(key, toStringNative(value), LogSupport::last, callable);
    }

    public static void withMdcContext(String key, String value, Runnable callable) {
        withMdcContext(key, value, LogSupport::last, callable);
    }

    @SneakyThrows
    public static void withMdcContext(String key, String value, Resolver resolver, Runnable callable) {
        String oldValue = MDC.get(key);
        try {
            MDC.put(key, resolver.apply(oldValue, value));

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

    public static String toIdentityHashCode(Object bean) {
        return String.valueOf(System.identityHashCode(bean));
    }

    public static String concatenate(String oldValue, String newValue) {
        return Optional.ofNullable(oldValue)
                .map(o -> o + "." + newValue)
                .orElse(newValue);
    }

    public static String last(String oldValue, String newValue) {
        return newValue;
    }

    @FunctionalInterface
    public interface Resolver extends BiFunction<String, String, String> {

    }
}
