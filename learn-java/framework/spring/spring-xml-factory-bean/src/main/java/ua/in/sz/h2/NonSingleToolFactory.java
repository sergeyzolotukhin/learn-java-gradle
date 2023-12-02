package ua.in.sz.h2;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class NonSingleToolFactory extends AbstractFactoryBean<Tool> {

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    protected Tool createInstance() {
        return new Tool();
    }
}
