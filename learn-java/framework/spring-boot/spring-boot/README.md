#### io.spring.dependency-management

https://nexocode.com/blog/posts/spring-dependencies-in-gradle/
https://github.com/spring-projects/spring-boot/issues/21723
https://stackoverflow.com/questions/72468537/is-io-spring-dependency-management-plugin-required-when-using-spring-boot-2-3-a
https://docs.gradle.org/current/userguide/java_platform_plugin.html
https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import
https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import

#### org.springframework.boot   Spring Boot Gradle plugin

https://gradlehero.com/unleashing-the-spring-boot-gradle-plugin/

#### Spring boot gradle plugin

https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#running-your-application

#### autowire-candidate
https://stackoverflow.com/questions/28374000/spring-programmatically-generate-a-set-of-beans
https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/custom-autowire-configurer.html
https://blog.monosoul.dev/2020/09/21/how-to-customize-dependency-injection-in-spring-part-1/

#### Spring Externalized Configuration
https://docs.spring.io/spring-boot/reference/features/external-config.html

#### Configuration Metadata
META-INF/spring-configuration-metadata.json
https://docs.spring.io/spring-boot/specification/configuration-metadata/annotation-processor.html

#### Auto Configure annotation
@SpringBootApplication
@EnableAutoConfiguration 

@SpringBootConfiguration
@AutoConfiguration
@AutoConfigurationPackage
@Configuration

---
AutoConfigureBefore
AutoConfigureAfter

Conditional
ConditionalOnClass
ConditionalOnMissingBean

---
AutoConfigurationImportSelector 
    -> EnableAutoConfiguration

AutoConfigurationPackages

=======================================================================================================================
6.2.1
6.2.3
6.2.10
=======================================================================================================================

ConfigurationClassPostProcessor --> BeanDefinitionRegistryPostProcessor
ConfigurationClassParser

https://www.devskillbuilder.com/spring-boot-auto-configuration-a-deep-dive-ab886e84fb74
