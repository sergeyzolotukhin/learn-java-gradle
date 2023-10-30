set JAVA_BIN=c:\soft\java-21\bin

%JAVA_BIN%\keytool -import ^
    -trustcacerts ^
    -alias pomochatserver ^
    -file my-client.cer ^
    -keystore my-truststore.ts ^
    -storepass password3 ^
    -noprompt