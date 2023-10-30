set JAVA_BIN=c:\soft\java-21\bin

rem     Reads from the keystore the certificate associated with alias and stores it in the cert_file file.
rem     When no file is specified, the certificate is output to stdout.

rem     This command was named -export in earlier releases. The old name is still supported in this release.
rem     The new name, -exportcert, is preferred going forward.

%JAVA_BIN%\keytool -export ^
    -alias pomochatserver ^
    -keystore server.keystore.jks ^
    -file server.cer ^
    -storepass password