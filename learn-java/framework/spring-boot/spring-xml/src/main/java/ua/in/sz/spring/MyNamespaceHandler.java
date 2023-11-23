package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

@Slf4j
public class MyNamespaceHandler extends NamespaceHandlerSupport {

	public void init() {
		registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
	}
}
