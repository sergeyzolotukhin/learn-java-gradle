#### Description

https://ongres.com/blog/java-processes-and-streams/

https://github.com/vorburger/ch.vorburger.exec
https://java.libhunt.com/zt-exec-alternatives

#### Java - jar + classpath

https://stackoverflow.com/questions/18413014/run-a-jar-file-from-the-command-line-and-specify-classpath

java 
    -classpath D:/projects-java/_learn-java-gradle/proof-of-concept/idea-debug-support/build/libs 
    -classpath .;D:/projects-java/_learn-java-gradle/proof-of-concept/idea-debug-support/build/libs 
    -classpath .;D:/projects-java/_learn-java-gradle/proof-of-concept/idea-debug-support/build/libs/idea-debug-support-0.0.1-SNAPSHOT.jar 
    -classpath D:\projects-java\_learn-java-gradle\proof-of-concept\idea-debug-support\build\libs\idea-debug-support-0.0.1-SNAPSHOT.jar 
    -Dfile.encoding=windows-1252 
    -Dsun.stdout.encoding=windows-1252 
    -Dsun.stderr.encoding=windows-1252 
    -jar D:\projects-java\_learn-java-gradle\learn-java\library\lib-apache-exec\build\libs\lib-apache-exec-1.0.jar