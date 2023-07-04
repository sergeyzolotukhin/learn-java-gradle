runtimeClasspath - Runtime classpath of source set 'main'.
+--- com.google.guava:guava:28.0-jre -> 28.1-jre
|    +--- com.google.guava:failureaccess:1.0.1
|    +--- com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava
|    +--- com.google.code.findbugs:jsr305:3.0.2
|    +--- org.checkerframework:checker-qual:2.8.1
|    +--- com.google.errorprone:error_prone_annotations:2.3.2
|    +--- com.google.j2objc:j2objc-annotations:1.3
|    \--- org.codehaus.mojo:animal-sniffer-annotations:1.18
+--- org.apache.cxf:cxf-rt-frontend-jaxws:3.3.6
|    +--- xml-resolver:xml-resolver:1.2
|    +--- org.ow2.asm:asm:7.1
|    +--- org.apache.cxf:cxf-core:3.3.6
|    |    +--- org.glassfish.jaxb:jaxb-runtime:2.3.2
|    |    |    +--- jakarta.xml.bind:jakarta.xml.bind-api:2.3.2
|    |    |    |    \--- jakarta.activation:jakarta.activation-api:1.2.1
|    |    |    +--- org.glassfish.jaxb:txw2:2.3.2
|    |    |    +--- com.sun.istack:istack-commons-runtime:3.0.8
|    |    |    |    \--- jakarta.activation:jakarta.activation-api:1.2.1
|    |    |    +--- org.jvnet.staxex:stax-ex:1.8.1
|    |    |    |    +--- jakarta.activation:jakarta.activation-api:1.2.1
|    |    |    |    \--- jakarta.xml.bind:jakarta.xml.bind-api:2.3.2 (*)
|    |    |    +--- com.sun.xml.fastinfoset:FastInfoset:1.2.16
|    |    |    \--- jakarta.activation:jakarta.activation-api:1.2.1
|    |    +--- com.fasterxml.woodstox:woodstox-core:5.0.3
|    |    |    \--- org.codehaus.woodstox:stax2-api:3.1.4
|    |    +--- org.apache.ws.xmlschema:xmlschema-core:2.2.5
|    |    \--- jakarta.xml.bind:jakarta.xml.bind-api:2.3.2 (*)
|    +--- org.apache.cxf:cxf-rt-bindings-soap:3.3.6
|    |    +--- org.apache.cxf:cxf-core:3.3.6 (*)
|    |    +--- org.apache.cxf:cxf-rt-wsdl:3.3.6
|    |    |    +--- org.apache.cxf:cxf-core:3.3.6 (*)
|    |    |    +--- wsdl4j:wsdl4j:1.6.3
|    |    |    \--- org.ow2.asm:asm:7.1
|    |    \--- org.apache.cxf:cxf-rt-databinding-jaxb:3.3.6
|    |         +--- org.apache.cxf:cxf-core:3.3.6 (*)
|    |         \--- org.apache.cxf:cxf-rt-wsdl:3.3.6 (*)
|    +--- org.apache.cxf:cxf-rt-bindings-xml:3.3.6
|    |    \--- org.apache.cxf:cxf-core:3.3.6 (*)
|    +--- org.apache.cxf:cxf-rt-frontend-simple:3.3.6
|    |    +--- org.apache.cxf:cxf-core:3.3.6 (*)
|    |    +--- org.apache.cxf:cxf-rt-bindings-soap:3.3.6 (*)
|    |    \--- org.apache.cxf:cxf-rt-wsdl:3.3.6 (*)
|    \--- org.apache.cxf:cxf-rt-ws-addr:3.3.6
|         +--- org.apache.cxf:cxf-core:3.3.6 (*)
|         +--- org.apache.cxf:cxf-rt-bindings-soap:3.3.6 (*)
|         \--- org.apache.cxf:cxf-rt-ws-policy:3.3.6
|              +--- wsdl4j:wsdl4j:1.6.3
|              +--- org.apache.cxf:cxf-core:3.3.6 (*)
|              \--- org.apache.neethi:neethi:3.1.1
+--- org.apache.cxf:cxf-rt-transports-http:3.3.6
|    \--- org.apache.cxf:cxf-core:3.3.6 (*)
+--- org.apache.cxf:cxf-rt-transports-http-jetty:3.3.6
|    +--- org.apache.cxf:cxf-core:3.3.6 (*)
|    +--- org.apache.cxf:cxf-rt-transports-http:3.3.6 (*)
|    +--- org.eclipse.jetty:jetty-server:9.4.27.v20200227
|    |    +--- javax.servlet:javax.servlet-api:3.1.0
|    |    +--- org.eclipse.jetty:jetty-http:9.4.27.v20200227
|    |    |    +--- org.eclipse.jetty:jetty-util:9.4.27.v20200227
|    |    |    \--- org.eclipse.jetty:jetty-io:9.4.27.v20200227
|    |    |         \--- org.eclipse.jetty:jetty-util:9.4.27.v20200227
|    |    \--- org.eclipse.jetty:jetty-io:9.4.27.v20200227 (*)
|    +--- org.eclipse.jetty:jetty-util:9.4.27.v20200227
|    +--- org.eclipse.jetty:jetty-io:9.4.27.v20200227 (*)
|    +--- org.eclipse.jetty:jetty-security:9.4.27.v20200227
|    |    \--- org.eclipse.jetty:jetty-server:9.4.27.v20200227 (*)
|    +--- org.eclipse.jetty:jetty-continuation:9.4.27.v20200227
|    +--- org.eclipse.jetty:jetty-http:9.4.27.v20200227 (*)
|    \--- org.slf4j:slf4j-api:1.7.29
+--- javax.annotation:javax.annotation-api:1.3.2
+--- javax.xml.ws:jaxws-api:2.3.1
|    +--- javax.xml.bind:jaxb-api:2.3.1
|    |    \--- javax.activation:javax.activation-api:1.2.0
|    +--- javax.xml.soap:javax.xml.soap-api:1.4.0
|    \--- javax.annotation:javax.annotation-api:1.3.2
+--- com.google.guava:guava:28.1-jre (*)
+--- org.apache.commons:commons-text:1.8
|    \--- org.apache.commons:commons-lang3:3.9
+--- org.apache.commons:commons-lang3:3.9
+--- org.apache.commons:commons-collections4:4.4
\--- ch.qos.logback:logback-classic:1.2.3
     +--- ch.qos.logback:logback-core:1.2.3
     \--- org.slf4j:slf4j-api:1.7.25 -> 1.7.29
