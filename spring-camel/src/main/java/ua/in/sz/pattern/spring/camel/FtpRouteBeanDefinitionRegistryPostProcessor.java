package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FtpRouteBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		for (int i = 0; i < 1; i++) {
			GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

			beanDefinition.setBeanClass(FtpInboundRoute.class);
			beanDefinition.getPropertyValues().add("port", i);

			registry.registerBeanDefinition(String.format("route-ftp-%d", i), beanDefinition);
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// NOOP
	}
}
