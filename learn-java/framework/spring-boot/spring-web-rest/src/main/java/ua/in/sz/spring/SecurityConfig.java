package ua.in.sz.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    @Order(1)
    SecurityFilterChain externalSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> {
                    csrf.disable();
                })
                .securityMatcher("/api/external/**")
                .authorizeHttpRequests(authorize -> {
                    authorize.anyRequest().authenticated();
                })
                .httpBasic(withDefaults())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new RequestBasicAuthenticationEntryPoint()));

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> {
                    csrf.disable();
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.anyRequest().authenticated();
                })
                .formLogin(withDefaults());

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
