-> 1. Create more one FTP use xml
=======================================================================================================================
1. Create more one FTP server (use BeanDefinition)
2. Create more one camel route (use BeanDefinition) 

Possible options:
1. BeanDefinition create more one route 
2. Recreate route each times
3. Create custom Camel FTP component (-cons: a lot of code)

Proffered approaches:
1. I want have one route with many source FTP

Open questions:
1. What is producer template?
2. What is adviceWith?
 
=======================================================================================================================

#### Embedded FTP server
    
    http://mockftpserver.sourceforge.net/
    testCompile group: 'org.mockftpserver', name: 'MockFtpServer', version: '2.7.1'

#### Dynamic FTP Client using Apache Camel and Spring

    https://blog.jayway.com/2010/08/12/dynamic-ftp-client-using-apache-camel-and-spring/

#### Camel custom component
    
    https://camel.apache.org/manual/latest/writing-components.html

#### Camel Producer template
    
    https://camel.apache.org/manual/latest/producertemplate.html
    producerTemplate.sendBodyAndHeader("ftp://user@host.com/remoteDirectory?password=secret", file, Exchange.FILE_NAME, file.getName());

#### Create or change route at runtime
    
    https://stackoverflow.com/questions/42999274/apache-camel-how-to-add-remove-endpoints-dynamically-from-a-route
    https://stackoverflow.com/questions/34311595/how-in-camel-to-add-and-start-routes-dynamically
    https://stackoverflow.com/questions/15248776/dynamic-change-endpoint-camel
    https://wiki.eclipse.org/Camel_Route:_Dynamic_Endpoint_using_PropertyPlaceholder
    
    