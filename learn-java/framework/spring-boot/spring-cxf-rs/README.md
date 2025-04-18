### How to see list of web services ?

http://localhost:8080/services

https://stackoverflow.com/questions/36709583/soapui-on-windows-10-high-dpi-4k-scaling-issue
https://cxf.apache.org/docs/client-http-transport-including-ssl-support.html#ClientHTTPTransport(includingSSLsupport)-ConfiguringSSLSupport
https://cxf.apache.org/docs/servlet-transport.html#ServletTransport-UsingtheservlettransportwithoutSpring

### 

https://medium.com/callibrity/jax-rs-integration-testing-with-apache-cxf-7248c90274ee

### jaxws
https://medium.com/turkcell/consuming-soap-service-with-apache-cxf-and-spring-5dce25a553

### jaxrs + security
https://stackoverflow.com/questions/55511943/apache-cxf-authentication-spring-security

https://www.ibm.com/docs/en/was-zos/9.0.5?topic=applications-securing-jax-rs-within-web-container

#### How to add custom auth supplier into the CXF

* Add auth via xml (providers or conduit) DONE
  https://cxf.apache.org/docs/jax-ws-configuration.html
  https://learningviacode.blogspot.com/2013/07/conduits-in-cxf.html
  https://cxf.apache.org/docs/client-http-transport-including-ssl-support.html

#### Questions

* How to integrate Auth into JUnit test ?
  * Junit extension -> Autowire 
  * Junit extension -> read annotation
https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations
https://github.com/junit-team/junit5/blob/r5.10.2/junit-jupiter-engine/src/main/java/org/junit/jupiter/engine/extension/TempDirectory.java
  * 
  
  * HttpClient without spring + custom auth
  * WS client without spring + custom auth 
  * WS client with spring + custom auth

#### Using JAX-RS With Spring Boot Instead of MVC

https://dzone.com/articles/using-jax-rs-with-spring-boot-instead-of-mvc
