http://localhost:8080/spring-web-xml/welcome.htm
Компьютер\HKEY_LOCAL_MACHINE\SOFTWARE\WOW6432Node\Apache Software Foundation\Procrun 2.0\Tomcat9\Parameters\Java

#### Documentation URLs

    https://docs.wildfly.org/19/Admin_Guide.html#Logging
    https://www.baeldung.com/infinispan

#### Window Linux subsystem
    
    tail ---disable-inotify -f application.2020-04-22.log

#### JMX

    service:jmx:remoting-jmx://localhost:8080
    service:jmx:remote://localhost:4447

CATALINA_HOME=c:\soft\apache-tomcat-10.1.14 

### 

Caused by: 
    org.springframework.beans.factory.NoSuchBeanDefinitionException: 
        No bean named 'mvcHandlerMappingIntrospector' available: 
        A Bean named mvcHandlerMappingIntrospector of type org.springframework.web.servlet.handler.HandlerMappingIntrospector 
is required to use MvcRequestMatcher. 
Please ensure Spring Security & Spring MVC are configured in a shared ApplicationContext.