package ua.in.sz.property.descriptor.processor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collection;

@Slf4j
@SuppressWarnings("unchecked")
public class BeanProcessor {

    public void process(Object bean) {
        if (Collection.class.isAssignableFrom(bean.getClass())) {
            processCollection((Collection<Object>) bean, null);
        } else {
            processObject(bean, null);
        }
    }

    // ================================================================================================================
    //
    // ================================================================================================================

    private void processCollection(Collection<?> collection, PropertyDescriptor pd) {
        for (Object object : collection) {
            processObject(object, pd);
        }
    }

    @SneakyThrows
    private void processObject(Object bean, PropertyDescriptor propertyDescriptor) {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            processProperty(bean, pd);
        }

        doProcessObject(bean);

//        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
//            processProperty(bean, pd);
//        }
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private void processProperty(Object bean, PropertyDescriptor pd) {
        if (!isSupported(pd)) {
            return;
        }

        Object propertyValue = pd.getReadMethod().invoke(bean);

//        log.info("pd {}={}", pd.getName(), propertyValue);
        if (Collection.class.isAssignableFrom(propertyValue.getClass())) {
            processCollection((Collection<Object>) propertyValue, pd);
        } else {
            processObject(propertyValue, pd);
        }
    }

    private void doProcessObject(Object bean) {
        log.info("processObject: {}", bean);
    }

    // ================================================================================================================
    // utils methods
    // ================================================================================================================

    private static boolean isSupported(PropertyDescriptor pd) {
        return !"class".equals(pd.getName())
                && !pd.getPropertyType().equals(String.class);
    }
}
