package ua.in.sz.pattern.spring.yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ToString
@ConfigurationProperties("acme")
public class AcmeProperties {
    /**
     * It is enabled something
     */
    private boolean enabled;
    private Security security;

    @Getter
    @Setter
    @ToString
    public static class Security {
        private String username = "superuser";
        private String password;
        private List<String> roles;
    }
}
