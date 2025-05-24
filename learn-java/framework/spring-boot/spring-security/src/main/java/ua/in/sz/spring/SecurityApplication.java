package ua.in.sz.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableWebSecurity(debug = true)
@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/mvc/**").authenticated())
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();
//    }
}
