package ua.in.sz.spring;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean<RequestDumperFilter> requestDumperFilterRegistration() {
        FilterRegistrationBean<RequestDumperFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestDumperFilter());
        registration.addUrlPatterns("/*");
        registration.setName("someFilter");
        registration.setOrder(1);

        return registration;
    }
}
