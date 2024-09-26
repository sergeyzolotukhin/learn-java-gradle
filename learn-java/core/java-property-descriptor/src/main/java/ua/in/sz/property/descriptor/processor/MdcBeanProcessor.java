package ua.in.sz.property.descriptor.processor;

import org.slf4j.MDC;
import ua.in.sz.property.descriptor.LogSupport;
import ua.in.sz.property.descriptor.model.Person;

import java.beans.PropertyDescriptor;

public class MdcBeanProcessor extends CleanBeanProcessor {

    @Override
    public void process(Object bean) {
        MDC.put("objectClass", Person.class.getSimpleName());
        LogSupport.withMdcContext("object", LogSupport.toIdentityHashCode(bean),
                () -> super.process(bean));
    }

    @Override
    protected void processProperty(Object parentBean, PropertyDescriptor pd) {
        LogSupport.withMdcContext("propertyPath", pd.getName(), LogSupport::concatenate,
                () -> super.processProperty(parentBean, pd));
    }

    @Override
    protected void processObjectFromProperty(Object propertyValue) {
        LogSupport.withMdcContext("objectIdentity", LogSupport.toIdentityHashCode(propertyValue),
                (oldValue, newValue) -> noneSupported(propertyValue) ? oldValue : LogSupport.last(oldValue, newValue),
                () -> super.processObjectFromProperty(propertyValue));
    }

    @Override
    protected void processObjectFromCollection(Object object) {
        LogSupport.withMdcContext("objectIdentity", LogSupport.toIdentityHashCode(object),
                () -> super.processObjectFromCollection(object));
    }

    private static boolean noneSupported(Object propertyValue) {
        return propertyValue instanceof Integer
                || propertyValue instanceof String;
    }
}
