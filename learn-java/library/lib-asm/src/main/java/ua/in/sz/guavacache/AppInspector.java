package ua.in.sz.guavacache;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

@Slf4j
public class AppInspector {
    public static void main(String[] args) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Scannee.class);
        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            log.info("property: [{}]", pd.getName());
        }
    }
}
