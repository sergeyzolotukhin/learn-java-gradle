set JAVA_BIN=c:\soft\java-21\bin

%JAVA_BIN%\keytool -import ^
    -trustcacerts ^
    -alias pomochatserver ^
    -file server.cer ^
    -keystore server.truststore.jks ^
    -storepass password ^
    -noprompt