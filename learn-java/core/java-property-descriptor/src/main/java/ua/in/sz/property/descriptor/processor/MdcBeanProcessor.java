package ua.in.sz.property.descriptor.processor;

import ua.in.sz.property.descriptor.LogSupport;

import java.beans.PropertyDescriptor;

public class MdcBeanProcessor extends CleanBeanProcessor {

    @Override
    public void process(Object bean) {
        LogSupport.withMdcContext("object", bean,
                () -> super.process(bean));
    }

    @Override
    protected void processProperty(Object parentBean, PropertyDescriptor pd) {
        LogSupport.withMdcContext("propertyPath", pd.getName(), LogSupport::concatenate,
                () -> super.processProperty(parentBean, pd));
    }

    @Override
    protected void processObjectFromProperty(Object propertyValue) {
        LogSupport.withMdcContext("object", propertyValue,
                () -> super.processObjectFromProperty(propertyValue));
    }

    @Override
    protected void processObjectFromCollection(Object object) {
        LogSupport.withMdcContext("object", object,
                () -> super.processObjectFromCollection(object));
    }
}
