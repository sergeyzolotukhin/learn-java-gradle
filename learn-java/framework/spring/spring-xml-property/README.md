### We registry a new xml handler
spring-context-6.0.12-sources.jar!/META-INF/spring.handlers
    http\://www.springframework.org/schema/context=org.springframework.context.config.ContextNamespaceHandler

### We register parser fpr a xml tag 
org.springframework.context.config.ContextNamespaceHandler
    registerBeanDefinitionParser("property-placeholder", new PropertyPlaceholderBeanDefinitionParser());

### We define a new spring bean for property resolving
PropertyPlaceholderBeanDefinitionParser
    return PropertySourcesPlaceholderConfigurer.class;

### We post procession spring bean with property resolver
PropertySourcesPlaceholderConfigurer 
    -> PlaceholderConfigurerSupport
        -> PlaceholderConfigurerSupport
            -> PropertyResourceConfigurer - BeanFactoryPostProcessor
                -> PropertiesLoaderSupport


### Programmatic access to properties created by property-placeholder
https://stackoverflow.com/questions/11415711/programmatic-access-to-properties-created-by-property-placeholder

https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/xsd-configuration.html

AnnotationConfigApplicationContext

#### What is PropertyPlaceholderConfigurer ?
<context:property-placeholder/>
PropertyPlaceholderAutoConfiguration
org.springframework.context.config.PropertyPlaceholderBeanDefinitionParser
https://dzone.com/articles/properties-spring

####
PropertySourcesPlaceholderConfigurer
PlaceholderConfigurerSupport
PropertyResourceConfigurer
PropertiesLoaderSupport

PropertySourcesPropertyResolver
AbstractPropertyResolver

PropertyPlaceholderHelper
PlaceholderParser

BeanDefinitionVisitor