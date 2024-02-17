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

#### Questions

* Add auth via bean post processor
* Add auth via xml (providers or conduit)
  https://cxf.apache.org/docs/jax-ws-configuration.html
  https://learningviacode.blogspot.com/2013/07/conduits-in-cxf.html
  https://cxf.apache.org/docs/client-http-transport-including-ssl-support.html



####

client.getClass().getInterfaces()

    interface org.apache.cxf.jaxrs.client.Client
    interface org.apache.cxf.jaxrs.client.InvocationHandlerAware
    interface java.io.Closeable

    interface ua.in.sz.pattern.spring.camel.WebRsService

interface org.apache.cxf.endpoint.Client"
interface jakarta.xml.ws.BindingProvider"
interface java.io.Closeable"

interface ua.in.sz.pattern.spring.camel.WebService"

####

                <xs:element ref="http-conf:authSupplier" 
                			minOccurs="0" maxOccurs="1">
                   <xs:annotation>
                     <xs:documentation>
                       Holds the bean reference or class name
                       of an object that supplies Auth information
                       both preemptively and in response to a 401 HTTP
                       Challenge. This class must extend the abstract class 
                       org.apache.cxf.transport.http.HttpBasicAuthSupplier.
                     </xs:documentation>
                   </xs:annotation>
                </xs:element>
