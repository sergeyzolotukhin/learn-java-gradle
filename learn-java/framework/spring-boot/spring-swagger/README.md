#  

http://localhost:8080/swagger-ui/index.html

{ "prop1": "PROP-1", "prop2": true, "prop3": 123 }
{
"prop1": "PROP-1",
"prop2": true,
"prop3": 123,
"prop4": {
"prop5": "PROP-1"
}
}

https://www.baeldung.com/openapi-json-query-parameters
https://www.bezkoder.com/swagger-3-annotations/
https://swagger.io/docs/specification/data-models/
https://tyk.io/learning-center/openapi-json-schema/
https://stackoverflow.com/questions/49574086/spring-boot-using-json-as-request-parameters-instead-of-an-entity-model
https://www.moesif.com/blog/technical/api-design/REST-API-Design-Best-Practices-for-Parameters-and-Query-String-Usage/

https://medium.com/isa-group/handling-inter-parameter-dependencies-in-rest-apis-with-idl4oas-e5489abac32f

swagger annotation
---

<dependency>
    <groupId>io.swagger.core.v3</groupId>
    <artifactId>swagger-annotations</artifactId>
    <version>2.2.22</version>
</dependency>

<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-annotations</artifactId>
    <version>2.0.0-rc2</version>
</dependency>

Rest controller
---
org.springdoc.webmvc.api.OpenApiWebMvcResource.openapiJson
org.springdoc.core.service.AbstractRequestService.build

annotations
---
// @RequestParam - spring mvc
// @Parameter - swagger parameter
// @QueryParam - JAX-RS specification

Json to Map
---
org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

https://github.com/swagger-api/swagger-core/tree/master/modules/swagger-annotations
https://github.com/swagger-api/swagger-core/releases/tag/v2.2.19

https://springfox.github.io/springfox/docs/current/