https://www.baeldung.com/java-json-binding-api
https://cowtowncoder.medium.com/jackson-2-11-features-40cdc1d2bdf3
https://www.baeldung.com/java-json-binding-api
https://stackoverflow.com/questions/56048893/why-is-javax-json-from-glassfish-needed-as-a-dependency-when-using-yasson-with-j
https://docs.wildfly.org/wildfly-proposals/jaxrs/WFLY-13585_Galleon_layer_jsonp_support.html

#### Java API for JSON Processing
https://javaee.github.io/jsonp/

#### Reference implementation
JSON-P is the reference implementation for JSR 374 (& 353). 
May or may not be a competitive one, in terms of feature and performance. 
This code is included as a part of Glassfish, the reference implementation of Java EE.

For the binding spec, JSR 367, the reference implementation is Eclipse Yasson.

https://softwarerecs.stackexchange.com/questions/47697/actual-implementation-of-jsr-353-json

JSR-353 Java API for JSON Processing
JSR-374 Java API for JSON Processing (JSON-P)
JSR-367 Java API for JSON Binding (JSON-B)

JSON Processing (JSON-P) is a Java API to process (for e.g. parse, generate, transform and query) JSON messages. 
It produces and consumes JSON text in a streaming fashion (similar to StAX API for XML) 
and allows to build a Java object model for JSON text using API classes (similar to DOM API for XML).

#### jar

JSR 374 (JSON Processing) API -> Relocated → jakarta.json » jakarta.json-api
https://mvnrepository.com/artifact/javax.json

Jakarta JSON Processing API
https://mvnrepository.com/artifact/jakarta.json/jakarta.json-api

JSR-367 Java API for JSON Binding (JSON-B)
https://mvnrepository.com/artifact/jakarta.json.bind/jakarta.json.bind-api