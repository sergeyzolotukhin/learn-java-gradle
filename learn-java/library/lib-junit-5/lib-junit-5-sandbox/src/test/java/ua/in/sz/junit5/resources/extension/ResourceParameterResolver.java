package ua.in.sz.junit5.resources.extension;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class ResourceParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(String.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        ResourceFile resourceFile = parameterContext.findAnnotation(ResourceFile.class).get();
        return asString(resourceFile.value());
    }

    @SneakyThrows
    private String asString(String resource) {
        try (InputStream inputStream = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resource))){
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
