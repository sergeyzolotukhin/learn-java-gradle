package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import java.text.SimpleDateFormat;

@Slf4j
public class SimpleDateFormatBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	protected Class getBeanClass(Element element) {
		return SimpleDateFormat.class;
	}

	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		String pattern = element.getAttribute("pattern");
//		bean.addConstructorArg(pattern);

		String lenient = element.getAttribute("lenient");
		if (StringUtils.hasText(lenient)) {
			bean.addPropertyValue("lenient", Boolean.valueOf(lenient));
		}
	}
}