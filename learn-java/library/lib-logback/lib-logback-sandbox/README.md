App running:

D:\projects-java\_learn_framework\patterns

Test running:

D:\projects-java\_learn_framework\patterns\library\lib-logback

https://stackoverflow.com/questions/60659731/slf4j-logging-to-different-files-based-on-a-tag

https://github.com/qos-ch/logback/blob/master/logback-examples/src/main/resources/chapters/filters/mdcfilter.xml
https://github.com/qos-ch/logback-extensions
https://reflectoring.io/logging-format-logback/
https://github.com/thombergs/descriptive-logger
https://github.com/qqq-tech/anthavio-spring/blob/6d0fb057efb30174ed80b42f93d188b4eceebfce/src/main/java/net/anthavio/logback/SpringTxMdcFilter.java
https://github.com/qos-ch/slf4j/blob/master/slf4j-ext/src/main/java/org/slf4j/NDC.java

https://www.slf4j.org/faq.html#marker
https://logback.qos.ch/manual/appenders.html#OnMarkerEvaluator

https://logback.qos.ch/manual/configuration.html

##### Per-Environment Logging with Plain Java and Spring Boot

https://stackoverflow.com/questions/35651241/configure-logback-using-several-profiles
https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/html/boot-features-logging.html#boot-features-custom-log-configuration
https://stackoverflow.com/questions/35651241/configure-logback-using-several-profiles
https://reflectoring.io/profile-specific-logging-spring-boot/

https://logback.qos.ch/manual/loggingSeparation.html#contextSelectors

#####

LoggerContext -|> ILoggerFactory -> getLogger

##### 

ch.qos.logback.classic.util.ContextInitializer.autoConfig(java.lang.ClassLoader)
    ClassicEnvUtil.loadFromServiceLoader
ch.qos.logback.classic.util.DefaultJoranConfigurator

#### How to check if class is exists in a logback

ch.qos.logback.core.model.processor.conditional.IfModelHandler
ch.qos.logback.core.joran.conditional.PropertyEvalScriptBuilder

org.springframework.boot\spring-boot\3.2.0
spring-boot-3.2.0.jar
\META-INF\services\ch.qos.logback.classic.spi.Configurator

org.springframework.boot.logging.logback.RootLogLevelConfigurator
org.springframework.boot.logging.logback.SpringBootJoranConfigurator

org.springframework.boot\spring-boot\3.2.0
spring-boot-3.2.0.jar
\META-INF\spring.factories

org.springframework.boot.logging.logback.LogbackLoggingSystem

https://stackoverflow.com/questions/5653062/how-can-i-configure-logback-to-log-different-levels-for-a-logger-to-different-de
https://stackoverflow.com/questions/1827677/how-to-do-a-junit-assert-on-a-message-in-a-logger
https://dzone.com/articles/unit-testing-log-messages-made-easy
https://skryvets.com/blog/2021/03/03/how-to-test-log-statements-in-spring/
https://signoz.io/blog/elk-alternatives/
https://betterstack.com/community/guides/logging/how-to-start-logging-with-java/

#### How to test logs with JUnit 5
https://www.baeldung.com/junit-asserting-logs
https://gist.github.com/jaykhimani/b12243e94a883e9470d706edd90b26ac
https://stackoverflow.com/questions/42766017/how-to-test-logging-in-junit5
https://medium.com/nerd-for-tech/log4j2-and-junit-5-how-to-test-logs-with-junit-5-e5fa3eec82d2

#### Intercepting and modifying logs in Logback
https://www.sedooe.com/2017/04/intercepting-and-modifying-logs-in-logback/


