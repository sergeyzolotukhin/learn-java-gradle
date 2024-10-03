package ua.in.szolotukhin.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CustomAnnotationsDeserializer extends DelegatingDeserializer {
    private final BeanDescription beanDescription;

    public CustomAnnotationsDeserializer(JsonDeserializer<?> delegate, BeanDescription beanDescription) {
        super(delegate);
        this.beanDescription = beanDescription;
    }

    @Override
    protected JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> newDelegatee) {
        return new CustomAnnotationsDeserializer(newDelegatee, beanDescription);
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object deserializedObject = super.deserialize(p, ctxt);

        log.info("post process: {} ", deserializedObject);
        return deserializedObject;
    }
}
