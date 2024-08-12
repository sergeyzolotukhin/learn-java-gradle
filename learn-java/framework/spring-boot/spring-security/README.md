###

WebSecurityConfiguration - configuration 
    -> depend from - HttpSecurity   
HttpSecurityConfiguration - configuration
HttpSecurity - builder

"org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration".equals(beanName)

https://www.baeldung.com/spring-boot-custom-auto-configuration
https://www.baeldung.com/spring-security-registered-filters

org.springframework.boot.autoconfigure.AutoConfigurationImportSelector.getCandidateConfigurations
org.springframework.boot.context.annotation.ImportCandidates.load
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration.WebSecurityEnablerConfiguration