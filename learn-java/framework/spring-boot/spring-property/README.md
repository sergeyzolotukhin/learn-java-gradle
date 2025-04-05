#### Run from windows command line

    java -jar spring-property.jar --spring.profiles.active=dev


https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.application.customize-the-environment-or-application-context

####  ConfigDataLocationResolver
https://docs.spring.io/spring-boot/api/kotlin/spring-boot-project/spring-boot/org.springframework.boot.context.config/-config-data-location-resolver/index.html

org.springframework.boot.context.config.ConfigDataImporter.resolveAndLoad
StandardConfigDataLocationResolver -|> ConfigDataLocationResolver

#### EnvironmentPostProcessor in Spring Boot
https://www.baeldung.com/spring-boot-environmentpostprocessor

#### Externalized Configuration
https://docs.spring.io/spring-boot/reference/features/external-config.html
https://master-spring-ter.medium.com/mastering-spring-boot-profiles-a-guide-to-environment-specific-configurations-646e7535db03

##### spring properties:

spring.config.name =
spring.config.location =
spring.config.additional-location =
spring.config.import = 

##### prefixes:

optional:
configtree:
file:
classpath:

##### Default list of property sources

Property sources: ConfigurationPropertySourcesPropertySource {name='configurationProperties'}
Property sources: PropertiesPropertySource {name='systemProperties'}
Property sources: OriginAwareSystemEnvironmentPropertySource {name='systemEnvironment'}
Property sources: RandomValuePropertySource {name='random'}
Property sources: OriginTrackedMapPropertySource {name='Config resource 'class path resource [application-dev.properties]' via location 'optional:classpath:/''}
Property sources: OriginTrackedMapPropertySource {name='Config resource 'class path resource [application.properties]' via location 'optional:classpath:/''}
Property sources: InstalationKitPropertySource {name='installation-kit-property-source'}
Property sources: ApplicationInfoPropertySource {name='applicationInfo'}
Property sources: LocalInstalationKitPropertySource {name='local-installation-kit-property-source'}

#### Spring Cloud Configuration

https://www.baeldung.com/spring-cloud-configuration
https://medium.com/@AlexanderObregon/a-beginners-guide-to-centralized-configuration-with-spring-cloud-config-6dfb6c70b5ad

