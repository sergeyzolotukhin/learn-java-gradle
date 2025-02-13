package ua.in.szolotukhin.jackson.reference.exists;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomSimpleObjectIdResolver extends SimpleObjectIdResolver {
    @Override
    public void bindItem(ObjectIdGenerator.IdKey id, Object ob) {
        log.info("bindItem {}", id);
        super.bindItem(id, ob);
    }

    @Override
    public Object resolveId(ObjectIdGenerator.IdKey id) {
        log.info("resolveId {}", id);
        return super.resolveId(id);
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return new CustomSimpleObjectIdResolver();
    }
}
