package ua.in.sz.pattern.spring.property;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationHook;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Map;

@Slf4j
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner {
    private final ConfigurableEnvironment env;

    @Autowired
    public Application(ConfigurableEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addInitializers(context -> context.getEnvironment()
                .getPropertySources()
                .addLast(new LocalInstalationKitPropertySource()));
        application.run(args);
    }

    @Override
    public void run(String... args) {
        log.info("Execute: [{}]", env.getProperty("pattern.text"));
        log.info("Execute build in: [{}]", env.getProperty("pattern.build.in.text"));
        log.info("Execute profile in: [{}]", env.getProperty("pattern.profile.dev.text"));
        log.info("Execute sz in: [{}]", env.getProperty("szprop"));
        log.info("Execute sz in: [{}]", env.getProperty("szprop-local"));

        MutablePropertySources propertySources = env.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            log.info("Property sources: {}", propertySource);
        }
    }

    private static class LocalInstalationKitPropertySource extends PropertySource<String> {
        private static final Map<String, String> PROPS = ImmutableMap.<String, String>builder()
                .put("szprop-local", "szvalue-local")
                .build() ;

        public LocalInstalationKitPropertySource() {
            super("local-installation-kit-property-source");
        }

        @Override
        public Object getProperty(String name) {
            return PROPS.get(name);
        }
    }
}
