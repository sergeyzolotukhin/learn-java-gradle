package ua.in.sz.property.descriptor.processor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collection;

@Slf4j
@SuppressWarnings("unchecked")
public class CleanBeanProcessor {

    public void process(Object bean) {
        if (Collection.class.isAssignableFrom(bean.getClass())) {
            processCollection((Collection<Object>) bean);
        } else {
            processObject(bean);
        }
    }

    // ================================================================================================================
    //
    // ================================================================================================================

    @SneakyThrows
    protected void processProperty(Object parentBean, PropertyDescriptor pd) {
        if (!isSupported(pd)) {
            return;
        }

        Object propertyValue = pd.getReadMethod().invoke(parentBean);
        processObjectFromProperty(propertyValue);
    }

    protected void processObjectFromProperty(Object propertyValue) {
        if (Collection.class.isAssignableFrom(propertyValue.getClass())) {
            processCollection((Collection<Object>) propertyValue);
        } else {
            processObject(propertyValue);
        }
    }

    private void processCollection(Collection<?> collection) {
        for (Object object : collection) {
            processObjectFromCollection(object);
        }
    }

    protected void processObjectFromCollection(Object object) {
        processObject(object);
    }

    @SneakyThrows
    private void processObject(Object bean) {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            processProperty(bean, pd);
        }

        doProcessObject(bean);
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
