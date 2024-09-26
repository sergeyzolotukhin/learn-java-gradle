package ua.in.sz.property.descriptor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

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
    private void processProperty(Object bean, PropertyDescriptor pd) {
        if (!isSupported(pd)) {
            return;
        }

        Object propertyValue = pd.getReadMethod().invoke(bean);

        if (Collection.class.isAssignableFrom(propertyValue.getClass())) {
            processCollection((Collection<Object>) propertyValue);
        } else {
            processObject(propertyValue);
        }
    }

    private void processCollection(Collection<?> collection) {
        for (Object object : collection) {
            processObject(object);
        }
    }

    @SneakyThrows
    private void processObject(Object bean) {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            withMdcContext(() -> processProperty(bean, pd), pd);
        }

        doProcessObject(bean);
    }

    private void doProcessObject(Object bean) {
        log.info("processObject: {}", bean);
    }

    // ================================================================================================================
    // utils methods
    // ================================================================================================================

    @SneakyThrows
    public static void withMdcContext(Runnable callable, PropertyDescriptor propertyDescriptor) {
        String originProperty = MDC.get("property");
        try {
            MDC.put("property", propertyDescriptor.getName());
            callable.run();
        } finally {
            if (originProperty == null) {
                MDC.remove("property");
            } else {
                MDC.put("property", originProperty);
            }
        }
    }

    private static boolean isSupported(PropertyDescriptor pd) {
        return !"class".equals(pd.getName())
                && !pd.getPropertyType().equals(String.class);
    }
}
