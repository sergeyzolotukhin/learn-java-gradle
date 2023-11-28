#### Use case
    IDEA -> Run Configuration -> bootRun

    update class in the subproject
    Ctrl+F5

    update class in the subproject
    Ctrl+F5
    
    ... 

05:36:02 PM: Executing 'bootRun'...
17:36:08.608 [main] INFO ua.in.sz.spring.Application -- path: [D:\projects-java\_learn-java-gradle\learn-java\framework\spring-boot\spring-dev-tools\build\classes\java\main]

Each iteration should took 6 seconds.

#### Spring Admin Console

    [Spring Admin](https://habr.com/ru/post/479954/)

#### Resolving gradle plugins from mavenLocal via pluginId

https://elmland.blog/2019/08/10/add-mavenlocal-to-gradle-plugin-resolution/

#### Class path when I rnu application via IDE

D:\projects-java\_learn-java-gradle\learn-java\framework\spring-boot\spring-dev-tools\build\classes\java\main]
D:\projects-java\_learn-java-gradle\learn-java\framework\spring-boot\spring-dev-tools\build\resources\main]
C:\Users\szolotukhin\.m2\repository\org\projectlombok\lombok\1.18.30\lombok-1.18.30.jar]
C:\Users\szolotukhin\.gradle\caches\modules-2\files-2.1\com.google.guava\guava\32.1.3-jre\f306708742ce2bf0fb0901216183bc14073feae\guava-32.1.3-jre.jar]
C:\Users\szolotukhin\.gradle\caches\modules-2\files-2.1\org.apache.commons\commons-text\1.11.0\2bb044b7717ec2eccaf9ea7769c1509054b50e9a\commons-text-1.11.0.jar]
D:\projects-java\_learn-java-gradle\learn-java\framework\spring-boot\spring-dev-tools\config

#### Class path when I rnu application via Gradle

D:\projects-java\_learn-java-gradle\learn-java\framework\spring-boot\spring-dev-tools\build\classes\java\main]
D:\projects-java\_learn-java-gradle\learn-java\framework\spring-boot\spring-dev-tools\build\resources\main]
D:\projects-java\_learn-java-gradle\learn-java\framework\spring-boot\spring-dev-tools\config]
C:\Users\szolotukhin\.gradle\caches\modules-2\files-2.1\org.springframework.boot\spring-boot-devtools\3.2.0\b8a547cba9b5ea40c1b2b0257bc0f19ebe4a0e17\spring-boot-devtools-3.2.0.jar]
C:\Users\szolotukhin\.gradle\caches\modules-2\files-2.1\com.google.guava\guava\32.1.3-jre\f306708742ce2bf0fb0901216183bc14073feae\guava-32.1.3-jre.jar]
C:\Users\szolotukhin\.gradle\caches\modules-2\files-2.1\org.apache.commons\commons-text\1.11.0\2bb044b7717ec2eccaf9ea7769c1509054b50e9a\commons-text-1.11.0.jar]

#### developmentOnly  -> org.springframework.boot

https://stackoverflow.com/questions/59252470/gradle-spring-boot-devtools-developmentonly-and-runtimeclasspath
https://docs.spring.io/spring-boot/docs/2.0.6.RELEASE/reference/html/using-boot-devtools.html