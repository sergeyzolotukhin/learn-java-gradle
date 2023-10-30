set JAVA_BIN=c:\soft\java-21\bin

rem     This command was named -genkey in earlier releases.  The old name is still supported in this release.
rem     The new name, -genkeypair, is preferred going forward.

rem     Generates a key pair (a public key and associated private key).
rem     Wraps the public key into an X.509 v3 self-signed certificate, which is stored as a single-element certificate chain.
rem     This certificate chain and the private key are stored in a new keystore entry identified by alias.

rem     While a keystore typically holds onto certificates that identify us, a truststore holds onto certificates that identify others.

rem     This certificate chain and the private key are stored in a new keystore entry that is identified by its alias.
rem     The value of -keypass is a password used to protect the private key of the generated key pair.
rem     While generating the keystore file using "keytool -genkey ..." we have a -keypass and -storepass.
rem     The -keypass option is used for "the password for the key" and the -storepass option is used for "a password for the keystore".
rem     When dname is provided, it is used as the subject of the generated certificate.

%JAVA_BIN%\keytool -genkey ^
    -alias pomochatserver ^
    -keyalg RSA ^
    -keystore my-keystore.jks ^
    -dname "cn=localhost, ou=IT, o=Continuent, c=US" ^
    -storepass password1 ^
    -keypass password2