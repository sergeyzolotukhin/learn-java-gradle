package ua.in.sz.hibernate.cache.events;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
public class LogInsertEventListener implements PostInsertEventListener {
    public static final LogInsertEventListener INSTANCE = new LogInsertEventListener();

    @Override
    public void onPostInsert(PostInsertEvent event) throws HibernateException {
        Object entity = event.getEntity();
        log.info("onPostInsert");
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}