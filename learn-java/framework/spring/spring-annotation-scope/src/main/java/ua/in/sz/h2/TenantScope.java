package ua.in.sz.h2;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

@SuppressWarnings("ALL")
public class TenantScope implements Scope {
    public static final String TENANT = "tenant";

    private Object object;
    private int count = 2;
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (--count < 0) {
            count = 2;
            object = objectFactory.getObject();
        } else {
            if (object == null) {
                object = objectFactory.getObject();
            }
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        Object result = object;
        object = null;
        count = 2;
        return object;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
