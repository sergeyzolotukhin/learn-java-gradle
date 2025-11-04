package ua.in.sz.hibernate.events.listeners.listeners;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import java.util.Objects;

@Slf4j
public abstract class AbstractOnChangePropertyValuePostUpdateEventListener implements PostUpdateEventListener {

    protected abstract boolean isSupportEntity(Object entity);

    protected abstract boolean isSupportProperty(String propertyName);

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        Object entity = event.getEntity();
        if (!isSupportEntity(entity)) {
            return;
        }

        Object[] oldState = event.getOldState();
        Object[] newState = event.getState();
        String[] propertyNames = event.getPersister().getPropertyNames();

        for (int i = 0; i < propertyNames.length; i++) {
            String propertyName = propertyNames[i];
            Object oldValue = oldState[i];
            Object newValue = newState[i];

            if (!isSupportProperty(propertyName)) {
                continue;
            }

            if (isChangedPropertyValue(oldValue, newValue)) {
                log.info("Property: [{}]: {} -> {}", propertyNames[i], oldValue, newValue);
            }
        }
    }

    private boolean isChangedPropertyValue(Object oldValue, Object newValue) {
        return !Objects.equals(oldValue, newValue);
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
