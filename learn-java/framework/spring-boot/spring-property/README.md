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

#### Type-safe Configuration Properties
https://docs.spring.io/spring-boot/reference/features/external-config.html#features.external-config.typesafe-configuration-properties

#### Spring Cloud Configuration

https://www.baeldung.com/spring-cloud-configuration
https://medium.com/@AlexanderObregon/a-beginners-guide-to-centralized-configuration-with-spring-cloud-config-6dfb6c70b5ad

#### HikariCP restart with Spring Cloud Config
https://stackoverflow.com/questions/54223822/hikaricp-restart-with-spring-cloud-config

#### Configuring Java applications at runtime with Spring Profiles
https://blog.scottlogic.com/2020/07/10/Spring-Profiles.html

####  Dynamic Property
https://docs.spring.io/spring-framework/reference/testing/testcontext-framework/ctx-management/dynamic-property-sources.html

https://medium.com/@anders.swanson.93/dynamically-load-spring-properties-from-external-locations-3ef644b42035

* The {@link PropertySource} will recursively scan a given source directory and expose a
* property for each file found. The property name will be the filename, and the property
* value will be the contents of the file

ConfigTreePropertySource

#### Spring Cloud Config Server utilizes a Git 
https://docs.spring.io/spring-cloud-config/reference/server/environment-repository/git-backend.html

#### Externalized Configuration
https://docs.spring.io/spring-boot/reference/features/external-config.html