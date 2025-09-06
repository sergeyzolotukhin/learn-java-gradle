package ua.in.sz.pattern.spring.binder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.HashMap;

@Slf4j
public class BinderApplication {

    public static void main(String[] args) {
        HashMap<String, Object> source = new HashMap<>();
        source.put("spring.output.ansi.enabled", "DETECT");

        StandardEnvironment environment = new StandardEnvironment();
        environment.getPropertySources().addFirst(new MapPropertySource("my-prop", source));

        Binder.get(environment)
                .bind("spring.output.ansi.enabled", AnsiOutput.Enabled.class)
                .ifBound(AnsiOutput::setEnabled);
    }
}
