package ua.in.sz.hibernate.cache.interceptors;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

@Slf4j
public class LoadInterceptor extends EmptyInterceptor {
    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("LoadInterceptor onLoad");
        return false;
    }
}
