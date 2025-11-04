package ua.in.sz.hibernate.events.listeners.listeners;

import ua.in.sz.hibernate.cascade.entities.Definition;

public class OnChangeDerivationStatusPostUpdateEventListener extends AbstractOnChangePropertyValuePostUpdateEventListener {
    @Override
    protected boolean isSupportEntity(Object entity) {
        return entity.getClass().equals(Definition.class);
    }

    @Override
    protected boolean isSupportProperty(String propertyName) {
        return "name".equals(propertyName);
    }
}
