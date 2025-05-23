###

WebSecurityConfiguration - configuration 
    -> depend from - HttpSecurity   
HttpSecurityConfiguration - configuration
HttpSecurity - builder

"org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration".equals(beanName)

https://www.baeldung.com/spring-boot-custom-auto-configuration
https://www.baeldung.com/spring-security-registered-filters
https://kasunprageethdissanayake.medium.com/spring-security-the-security-filter-chain-2e399a1cb8e3
https://docs.spring.io/spring-framework/reference/web/websocket/stomp/message-flow.html

org.springframework.boot.autoconfigure.AutoConfigurationImportSelector.getCandidateConfigurations
org.springframework.boot.context.annotation.ImportCandidates.load
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration.WebSecurityEnablerConfiguration

https://grails.github.io/grails-spring-security-ui/4.0.x/index.html
https://www.keycloak.org/