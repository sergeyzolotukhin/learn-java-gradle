https://github.com/FasterXML/jackson-databind/issues/1196
https://github.com/FasterXML/jackson-databind/issues/1207
https://github.com/FasterXML/jackson

DeserializationProblemHandler

https://stackoverflow.com/questions/48773728/configure-a-jacksons-deserializationproblemhandler-in-spring-environment

#### JSON validation

https://guillaumeblanchet.medium.com/three-ways-to-validate-json-in-java-4b38d95ba7c
https://www.baeldung.com/java-object-validation-deserialization
https://www.baeldung.com/java-validate-json-string
json-schema-validator


lib-junit-5-hamcrest


https://stackoverflow.com/questions/6834677/jackson-mapper-post-construct/6834831#6834831
https://www.baeldung.com/java-json-schema-create-automatically

https://stackoverflow.com/questions/66483578/spring-jackson-deserialize-object-with-reference-to-existing-object-by-id

#### @JsonIdentityInfo, @JsonIdentityReference
https://www.baeldung.com/jackson-advanced-annotations
https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonidentityinfo.htm

#### ObjectIdResolver
https://stackoverflow.com/questions/41989906/jackson-referencing-an-object-as-a-property
https://github.com/ralscha/attic/blob/master/jacksonhibernate/src/main/java/ch/rasc/jacksonhibernate/Start.java

#### JacksonInject
https://www.concretepage.com/jackson-api/jackson-jacksoninject-example
org.springframework.http.converter.json.SpringHandlerInstantiator.resolverIdGeneratorInstance

#### jackson with spring bean
https://www.baeldung.com/spring-boot-jsoncomponent
spring-boot-3.4.1.jar

#### Jackson Mixin
https://medium.com/@shankar.ganesh.1234/jackson-mixin-a-simple-guide-to-a-powerful-feature-d984341dc9e2

#### Jackson Streaming API
https://www.baeldung.com/jackson-streaming-api

#### Merging Two JSON Documents Using Jackson
https://stackoverflow.com/questions/9895041/merging-two-json-documents-using-jackson

MyBean defaults = objectMapper.readValue(defaultJson, MyBean.class);
ObjectReader updater = objectMapper.readerForUpdating(defaults);
MyBean merged = updater.readValue(overridesJson);

#### @JsonMerge
https://www.baeldung.com/java-jsonmerge-annotation-jackson

#### Mergeable spring

#### merge pojo
https://github.com/amareensaleh/merge-pojos/tree/master
https://medium.com/@joantolos/merging-two-objects-in-java-5bc43cd8ab74

org.apache.commons.beanutils.BeanUtils.copyProperties

#### merge with mapstruct
https://www.youtube.com/watch?v=wBb7GcwYBnM&list=WL&index=1&ab_channel=GiuseppeScaramuzzino
mapstruct processor ???
@MappingTarget

#### yaml
https://www.baeldung.com/java-snake-yaml 

#### JsonPath
https://github.com/json-path/JsonPath

https://medium.com/@davenkin_93074/jacksons-default-serialization-deserialization-behavior-ed3d6dcf239b

####

com.fasterxml.jackson.databind.ObjectMapper.createDeserializationContext

com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer._fromString

use a json schema validation

#### Spring - JSON Schema Validator
https://medium.com/@mohommad.belal/json-schema-validator-using-java-springboot-667ed42480d5
https://www.baeldung.com/introduction-to-json-schema-in-java
https://www.mscharhag.com/spring/json-schema-validation-handlermethodargumentresolver
https://medium.com/@bytewise010/json-schema-validation-in-spring-boot-c5ed0b48f011

####

https://www.baeldung.com/jackson-streaming-api
https://medium.com/@akademixs247/jackson-objectmapper-tips-and-tricks-optimizing-performance-in-java-applications-09d5acffeb3d
