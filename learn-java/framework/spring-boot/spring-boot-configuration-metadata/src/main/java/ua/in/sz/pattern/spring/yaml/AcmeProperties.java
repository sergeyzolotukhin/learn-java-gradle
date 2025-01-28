package ua.in.sz.pattern.spring.yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.Arrays;
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
    /**
     * It is about security
     */
    @NestedConfigurationProperty
    private Security security;

    @Getter
    @Setter
    @ToString
    public static class Security {
        private String username = "superuser";
        private String password = "password";
        /**
         * This is a list of role names
         */
        private List<String> roles = Arrays.asList("user", "admin");
    }
}
