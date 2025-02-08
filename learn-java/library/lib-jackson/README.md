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
