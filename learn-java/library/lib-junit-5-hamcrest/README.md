# junit-hamcrest
http://hamcrest.org/JavaHamcrest/tutorial
https://dzone.com/refcardz/mockito?chapter=5

https://github.com/junit-team/junit5/wiki/Third-party-Extensions
http://www.joshka.net/junit-json-params/

#####
com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT

com.fasterxml.jackson.databind.ObjectMapper
-> writeValueAsString
    com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
        -> serializeValue(com.fasterxml.jackson.core.JsonGenerator, java.lang.Object)
            -> JsonSerializer<Object> ser = findTypedValueSerializer(cls, true, null);
                -> TypeSerializer typeSer = _serializerFactory.createTypeSerializer(_config, _config.constructType(valueType));
            -> ser.serialize(value, gen, this);

=======================================================================================================================

com.fasterxml.jackson.databind.ser.BeanSerializerFactory
-> findBeanProperties
    -> com.fasterxml.jackson.databind.ser.PropertyBuilder.buildWriter
        -> defaultBean = getDefaultBean()
        -> valueToSuppress = am.getValue(defaultBean);
        -> BeanPropertyWriter bpw = _constructPropertyWriter(propDef, am, _beanDesc.getClassAnnotations(), declaredType, ser, typeSer, serializationType, suppressNulls, 
                valueToSuppress, views);

=======================================================================================================================

ObjectMapper -> DefaultSerializerProvider
-> IndexedListSerializer -> AsArraySerializerBase 
-> PropertySerializerMap 
-> DefaultSerializerProvider
-> BeanSerializerFactory -> PropertyBuilder -> BasicBeanDescription -> AnnotatedConstructor -> constructor

=======================================================================================================================

ObjectMapper -> DefaultSerializerProvider
-> IndexedListSerializer -> BeanSerializer -> BeanPropertyWriter -> equal

=======================================================================================================================
ObjectMapper -> writeValueAsString -> serialize -> create serializer -> serialize -> create serializer -> ...
=======================================================================================================================
https://www.baeldung.com/jackson-serialize-field-custom-criteria
https://www.logicbig.com/tutorials/misc/jackson/json-include-customized.html
https://www.baeldung.com/jackson-annotations

