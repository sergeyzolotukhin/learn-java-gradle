https://www.jenkins.io/doc/developer/building/intellij/
https://www.jenkins.io/doc/developer/
https://github.com/jenkinsci/jenkins

=======================================================================================================================

![](https://github.com/sergeyzolotukhin/patterns/workflows/Java%20CI%20with%20Gradle/badge.svg)

pip install pywin32

#### Properties

What is the best particles for property management?  

#### Logging
* How to conditional logback config
* Show log header
* Filter stack trace in exception

#### Debug
* How to add custom debug view in IDEA
* How to execute code when running from IDEA
* How to filer stack trace in IDEA

#### CI 

How to backup and share database state with CI?

#### custom schema handler

    https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/xsd-config.html
    https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/extensible-xml.html
    
#### Method references 

    https://dzone.com/articles/java-lambda-method-reference
    
#### "Prefer extension points over features" @ 

    https://dzone.com/refcardz/drools?chapter=3
    
#### Docker persistence

    volume plugin. StorageOS 
    Host volumes, 
    Shared volumes or file systems. named volumes, NFS as the shared filesystem, Manta Objects, or other object storage
    
#### Maven profiler

    https://github.com/jcgay/maven-profiler#installation
    https://github.com/takari/maven-profiler
    https://medium.com/@alex_collins/10x-faster-maven-builds-at-intuit-5b7bb60c65e6
    
#### Maven

    https://maven.apache.org/guides/introduction/introduction-to-profiles.html
    
#### DJVU
    
    djvudump.exe -o book-1.metoinfo book-1.djvu
    djvmcvt.exe -i book-1.djvu book-1 book-1.djvu
    ddjvu -format=tiff -page=1 book-1.djvu book-1.tiff
    https://sourceforge.net/projects/djvu/files/DjVuLibre/3.5.27/djvulibre-3.5.27.tar.gz/download?use_mirror=netcologne
    
#### Vagrant 

    https://dotsandbrackets.com/vagrant-windows-ru/
    https://dev.to/jeikabu/reusable-windows-vms-with-vagrant-2h5c
    https://medium.com/@gajbhiyedeepanshu/building-custom-vagrant-box-e6a846b6baca
    https://softwaretester.info/create-private-vagrant-box-repository/
    
#### Windows service 

    https://commons.apache.org/proper/commons-daemon/    
    https://medium.com/@adam_carroll/java-packager-with-jdk11-31b3d620f4a8
    
#### Audit trail 

    audit4j
    Logstash
    Filebeat
    
    https://dzone.com/articles/7-log-management-tools-java
    http://www.dissmeyer.com/2017/11/11/installing-logstash-on-windows/
    https://www.elastic.co/guide/en/logstash/current/running-logstash-windows.html
    
#### Spring runtime plugin

    java.util.ServiceLoader
    org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean
    META-INF/spring.factories
    org.springframework.core.io.support.SpringFactoriesLoader
    Load-time weaving with AspectJ in the Spring Framework
    
#### Hibernate
    
    https://dzone.com/articles/hibernate-5-java-configuration-example    

#### GIT
    
    https://blog.osteele.com/2008/05/my-git-workflow/
    git grep "List<Range<LocalTime>> merge = Application.merge(ranges);"
    
#### Pattern
    
    saga    https://github.com/fernandoBRS/microservices-transactions
            https://bool.dev/blog/detail/saga-pattern-i-raspredelennye-tranzaktsii
            https://dzone.com/articles/saga-pattern-how-to-implement-business-transaction
            https://dzone.com/articles/microservices-and-the-saga-pattern-part-1
            https://dzone.com/articles/microservices-and-the-saga-pattern-part-2
            https://dzone.com/articles/microservices-and-the-saga-pattern-part-3
            
            https://en.wikipedia.org/wiki/Software_design_pattern
            https://www.javacodegeeks.com/2014/01/java-using-the-specification-pattern-with-jpa.html
            https://www.sourcecodeexamples.net/p/p-of-eaa.html
            https://refactoring.guru/ru/refactoring/catalog
            https://www.sourcecodeexamples.net/2018/05/refactoring-chain-of-responsibility.html
            
#### AWS
    
    Ovewriew was readed:
        AWS EC-2
        AWS BATCH JOBS
        DynamoDB
        CloudFoundation  https://asha.com.ua/simpleaboutcloudformation/
    
    Unknown topics:
        STEP FUNCTIONS
        LAMBDA FUNCTIONS
        CloudWatch
    
    
    Terraform
    
https://dou.ua/lenta/articles/rails-tutorial-docker-2/
    
### Get exit code windows
    
    java Foo
    echo %ERRORLEVEL%
    
#### Best practices

    A good exception message should explain what is wrong (in this case, "why it is wrong") and how to fix it
    https://dzone.com/refcardz/designing-quality-software?chapter=3
    
#### AccessExceptionError

    https://examples.javacodegeeks.com/java-basics/exceptions/java-lang-illegalaccesserror-how-to-resolve-illegal-access-error/
    
#### How to artifact publish to github maven repository
    
    curl -X PUT "https://maven.pkg.github.com/sergeyzolotukhin/patterns/ua/in/sz/pattern/pattern-gof-adapter/1.0.0/pattern-gof-adapter-1.0.0.jar" 
    -H "Authorization: token <GIT_HUB_TOKEN>" 
    --upload-file "<jar path>" -vvv
    
#### How to set system environment variables   

    https://superuser.com/questions/949560/how-do-i-set-system-environment-variables-in-windows-10
    
#### How to checkout specific version for folder GIT

    git archive --output=./gof-a-v0.0.1.zip HEAD
    git archive --output=./gof-a-v0.0.1.zip v0.0.1 ./pattern-gof-adapter
    
    7z x gof-a-v0.0.1.zip -obuild\gof-a-v0.0.1 > unzip.log

#### 7-Zip
    https://sevenzip.osdn.jp/chm/cmdline/syntax.htm
    https://sevenzip.osdn.jp/chm/cmdline/commands/index.htm
    
#### Vim

    https://i-notes.org/vimtutor-uchebnik-vim-versiya-1-7/
    
#### Other
    Swagger
    OAuth2
    Microservices
    Kafka
    RabbitMQ
    AWS 
    React
    
    Test email notification

#### Logger

    http://logback.qos.ch/manual/filters.html

#### Spring Boot Dockerize

    https://crowdsalat.github.io/technotes/spring/spring-boot-docker/
==== IP TV

- X96
- X98
- X95 android

- Xiomy

#### Project Layout

Monolithic Modular Architecture
https://medium.com/@abel.ncm/monolithic-modular-architecture-modular-folder-organization-4cbf97175ab4


Clean Architecture
https://www.milanjovanovic.tech/blog/clean-architecture-folder-structure
https://medium.com/@viniciusromualdobusiness/clean-architecture-with-spring-boot-a-good-idea-d6f97e450130
https://medium.com/@matheusarjc/clean-architecture-with-spring-boot-723701c58b9f

Layered Architecture
https://levelup.gitconnected.com/how-to-differentiate-business-and-service-layers-in-layered-architecture-912123b2ccf1
https://labs.madisoft.it/folder-structure-for-big-projects-package-by-type-layer-or-feature/

Microservice Architecture
https://github.com/Muddz/StructurebyFeatureTmpl
https://www.springfuse.com/java-microservices-project-structure-essentials/

Microservices Monorepo vs. Polyrepo
https://www.linkedin.com/pulse/simplifying-microservices-management-monorepo-vs-polyrepo-miqbal/
https://circleci.com/blog/monorepo-dev-practices/

Shared libraries in microservices
https://medium.com/@shanthi.shyju/shared-libraries-in-microservices-avoiding-an-antipattern-c9a3161276e
