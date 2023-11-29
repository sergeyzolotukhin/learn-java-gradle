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

### How spring covert list to the vararray

org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues

org.springframework.beans.factory.support.AbstractBeanFactory.getCustomTypeConverter


RuntimeBeanReference

==
resolvedValue = java.util.ArrayList<String>
converter = org.springframework.beans.BeanWrapperImpl: wrapping object [ua.in.sz.h2.BusinessService@3f91b517]

convertedValue = convertForProperty(resolvedValue, propertyName, bw, converter);
org.springframework.beans.AbstractNestablePropertyAccessor.convertIfNecessary
org.springframework.beans.TypeConverterDelegate.findDefaultEditor
org.springframework.beans.PropertyEditorRegistrySupport.getDefaultEditor
org.springframework.beans.TypeConverterDelegate.convertToTypedArray

org.springframework.beans.propertyeditors.StringArrayPropertyEditor
