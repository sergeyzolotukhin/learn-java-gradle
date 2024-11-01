package ua.in.sz.pattern.spring.autowired;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

// org.springframework.beans.factory.support.ConstructorResolver.resolvePreparedArguments
@Slf4j
@Configuration
@SpringBootApplication
public class AutowiredApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AutowiredApplication.class, args);

        Map<String, RuleService> beans = context.getBeansOfType(RuleService.class);
        for (String name : beans.keySet()) {
            log.info("bean name: {}", name);
        }

        ManagerService bean = context.getBean(ManagerService.class);
        bean.print();
    }

    @Bean
    public ManagerService managerService(@Qualifier("package-A") List<RuleService> rules) {
        return new ManagerService(rules);
    }

    @Bean
    @Qualifier("package-A")
    public RuleService firstRuleService() {
        return new RuleAService("rule 1");
    }

    @Bean
    @Qualifier("package-A")
    public RuleService secondRuleService() {
        return new RuleAService("rule 2");
    }

    @Bean
    public RuleService thirdRuleService() {
        return new RuleAService("rule 3");
    }
}
