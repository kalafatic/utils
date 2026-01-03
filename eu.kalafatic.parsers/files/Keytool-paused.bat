@ECHO OFF
REM.-- Prepare the Command Processor
SETLOCAL ENABLEEXTENSIONS
SETLOCAL ENABLEDELAYEDEXPANSION

REM.-- code goes here

echo Generate a Java keystore and key pair ...

keytool -genkey -alias gemini -keyalg RSA -keystore keystore.jks

::==

REM.--Generate a certificate signing request (CSR) for an existing Java keystore
REM.--keytool -certreq -alias "mydomain" -keystore keystore.jks -file mydomain.csr

REM.--Generate a keystore and self-signed certificate
REM.--keytool -genkey -keyalg RSA -alias "selfsigned" -keystore keystore.jks -storepass "password" -validity 360

REM.--Check which certificates are in a Java keystore
REM.--keytool -list -v -keystore keystore.jks

REM.--Delete a certificate from a Java Keytool keystore
REM.--keytool -delete -alias "mydomain" -keystore keystore.jks

REM.--Change a Java keystore password
REM.--keytool -storepasswd -new new_storepass -keystore keystore.jks

REM.--Export a certificate from a keystore
REM.--keytool -export -alias mydomain -file mydomain.crt

REM.--List Trusted CA Certs
REM.--keytool -list -v -keystore $JAVA_HOME/jre/lib/security/cacerts

REM.--Import New CA into Trusted Certs
REM.--keytool -import -trustcacerts -file /path/to/ca/ca.pem -alias CA_ALIAS 
REM.---keystore $JAVA_HOME/jre/lib/security/cacerts

echo Konec davky.

REM.-- End of application
ECHO.&ECHO.Press any key to end the application.
PAUSE>NUL&GOTO:EOF