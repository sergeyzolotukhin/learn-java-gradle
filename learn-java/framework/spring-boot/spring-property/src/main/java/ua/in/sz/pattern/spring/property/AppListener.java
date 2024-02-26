package ua.in.sz.pattern.spring.property;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.Map;

@Slf4j
public class AppListener implements SpringApplicationRunListener {
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        environment.getPropertySources().addLast(new InstalationKitPropertySource());
    }

    private static class InstalationKitPropertySource extends PropertySource<String> {
        private static final Map<String, String> PROPS = ImmutableMap.<String, String>builder()
                .put("szprop", "szvalue")
                .build() ;


        public InstalationKitPropertySource() {
            super("installation-kit-property-source");
        }

        @Override
        public Object getProperty(String name) {
            return PROPS.get(name);
        }
    }
}
