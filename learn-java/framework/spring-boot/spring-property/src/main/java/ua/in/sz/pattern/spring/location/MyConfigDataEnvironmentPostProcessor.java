package ua.in.sz.pattern.spring.location;

import com.google.common.collect.ImmutableMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

public class MyConfigDataEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered  {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> map = ImmutableMap.<String, Object>builder()
                .put("default-local", "default-local-serhij-01")
                .build();

        environment.getPropertySources()
                .addLast(new MapPropertySource("my-props", map));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
