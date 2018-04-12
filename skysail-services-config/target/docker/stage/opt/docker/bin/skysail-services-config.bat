@REM skysail-services-config launcher script
@REM
@REM Environment:
@REM JAVA_HOME - location of a JDK home dir (optional if java on path)
@REM CFG_OPTS  - JVM options (optional)
@REM Configuration:
@REM SKYSAIL_SERVICES_CONFIG_config.txt found in the SKYSAIL_SERVICES_CONFIG_HOME.
@setlocal enabledelayedexpansion

@echo off

if "%SKYSAIL_SERVICES_CONFIG_HOME%"=="" set "SKYSAIL_SERVICES_CONFIG_HOME=%~dp0\\.."

set "APP_LIB_DIR=%SKYSAIL_SERVICES_CONFIG_HOME%\lib\"

rem Detect if we were double clicked, although theoretically A user could
rem manually run cmd /c
for %%x in (!cmdcmdline!) do if %%~x==/c set DOUBLECLICKED=1

rem FIRST we load the config file of extra options.
set "CFG_FILE=%SKYSAIL_SERVICES_CONFIG_HOME%\SKYSAIL_SERVICES_CONFIG_config.txt"
set CFG_OPTS=
if exist "%CFG_FILE%" (
  FOR /F "tokens=* eol=# usebackq delims=" %%i IN ("%CFG_FILE%") DO (
    set DO_NOT_REUSE_ME=%%i
    rem ZOMG (Part #2) WE use !! here to delay the expansion of
    rem CFG_OPTS, otherwise it remains "" for this loop.
    set CFG_OPTS=!CFG_OPTS! !DO_NOT_REUSE_ME!
  )
)

rem We use the value of the JAVACMD environment variable if defined
set _JAVACMD=%JAVACMD%

if "%_JAVACMD%"=="" (
  if not "%JAVA_HOME%"=="" (
    if exist "%JAVA_HOME%\bin\java.exe" set "_JAVACMD=%JAVA_HOME%\bin\java.exe"
  )
)

if "%_JAVACMD%"=="" set _JAVACMD=java

rem Detect if this java is ok to use.
for /F %%j in ('"%_JAVACMD%" -version  2^>^&1') do (
  if %%~j==java set JAVAINSTALLED=1
  if %%~j==openjdk set JAVAINSTALLED=1
)

rem BAT has no logical or, so we do it OLD SCHOOL! Oppan Redmond Style
set JAVAOK=true
if not defined JAVAINSTALLED set JAVAOK=false

if "%JAVAOK%"=="false" (
  echo.
  echo A Java JDK is not installed or can't be found.
  if not "%JAVA_HOME%"=="" (
    echo JAVA_HOME = "%JAVA_HOME%"
  )
  echo.
  echo Please go to
  echo   http://www.oracle.com/technetwork/java/javase/downloads/index.html
  echo and download a valid Java JDK and install before running skysail-services-config.
  echo.
  echo If you think this message is in error, please check
  echo your environment variables to see if "java.exe" and "javac.exe" are
  echo available via JAVA_HOME or PATH.
  echo.
  if defined DOUBLECLICKED pause
  exit /B 1
)


rem We use the value of the JAVA_OPTS environment variable if defined, rather than the config.
set _JAVA_OPTS=%JAVA_OPTS%
if "!_JAVA_OPTS!"=="" set _JAVA_OPTS=!CFG_OPTS!

rem We keep in _JAVA_PARAMS all -J-prefixed and -D-prefixed arguments
rem "-J" is stripped, "-D" is left as is, and everything is appended to JAVA_OPTS
set _JAVA_PARAMS=
set _APP_ARGS=

:param_loop
call set _PARAM1=%%1
set "_TEST_PARAM=%~1"

if ["!_PARAM1!"]==[""] goto param_afterloop


rem ignore arguments that do not start with '-'
if "%_TEST_PARAM:~0,1%"=="-" goto param_java_check
set _APP_ARGS=!_APP_ARGS! !_PARAM1!
shift
goto param_loop

:param_java_check
if "!_TEST_PARAM:~0,2!"=="-J" (
  rem strip -J prefix
  set _JAVA_PARAMS=!_JAVA_PARAMS! !_TEST_PARAM:~2!
  shift
  goto param_loop
)

if "!_TEST_PARAM:~0,2!"=="-D" (
  rem test if this was double-quoted property "-Dprop=42"
  for /F "delims== tokens=1,*" %%G in ("!_TEST_PARAM!") DO (
    if not ["%%H"] == [""] (
      set _JAVA_PARAMS=!_JAVA_PARAMS! !_PARAM1!
    ) else if [%2] neq [] (
      rem it was a normal property: -Dprop=42 or -Drop="42"
      call set _PARAM1=%%1=%%2
      set _JAVA_PARAMS=!_JAVA_PARAMS! !_PARAM1!
      shift
    )
  )
) else (
  if "!_TEST_PARAM!"=="-main" (
    call set CUSTOM_MAIN_CLASS=%%2
    shift
  ) else (
    set _APP_ARGS=!_APP_ARGS! !_PARAM1!
  )
)
shift
goto param_loop
:param_afterloop

set _JAVA_OPTS=!_JAVA_OPTS! !_JAVA_PARAMS!
:run

set "APP_CLASSPATH=%APP_LIB_DIR%\io.skysail.skysail-services-config-0.1.jar;%APP_LIB_DIR%\org.scala-lang.scala-library-2.11.12.jar;%APP_LIB_DIR%\org.springframework.cloud.spring-cloud-config-server-2.0.0.M9.jar;%APP_LIB_DIR%\org.springframework.cloud.spring-cloud-config-client-2.0.0.M9.jar;%APP_LIB_DIR%\org.springframework.cloud.spring-cloud-commons-2.0.0.M9.jar;%APP_LIB_DIR%\org.springframework.security.spring-security-crypto-5.0.3.RELEASE.jar;%APP_LIB_DIR%\org.apache.httpcomponents.httpclient-4.5.4.jar;%APP_LIB_DIR%\org.apache.httpcomponents.httpcore-4.4.7.jar;%APP_LIB_DIR%\commons-codec.commons-codec-1.10.jar;%APP_LIB_DIR%\org.springframework.cloud.spring-cloud-context-2.0.0.M9.jar;%APP_LIB_DIR%\org.springframework.spring-web-5.0.4.RELEASE.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-annotations-2.9.0.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-databind-2.9.4.jar;%APP_LIB_DIR%\com.fasterxml.jackson.core.jackson-core-2.9.4.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-starter-actuator-2.0.0.RELEASE.jar;%APP_LIB_DIR%\ch.qos.logback.logback-classic-1.2.3.jar;%APP_LIB_DIR%\ch.qos.logback.logback-core-1.2.3.jar;%APP_LIB_DIR%\org.slf4j.slf4j-api-1.7.25.jar;%APP_LIB_DIR%\org.apache.logging.log4j.log4j-to-slf4j-2.10.0.jar;%APP_LIB_DIR%\org.slf4j.jul-to-slf4j-1.7.25.jar;%APP_LIB_DIR%\javax.annotation.javax.annotation-api-1.3.2.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-actuator-autoconfigure-2.0.0.RELEASE.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-actuator-2.0.0.RELEASE.jar;%APP_LIB_DIR%\io.micrometer.micrometer-core-1.0.1.jar;%APP_LIB_DIR%\org.hdrhistogram.HdrHistogram-2.1.10.jar;%APP_LIB_DIR%\org.latencyutils.LatencyUtils-2.0.3.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-starter-web-2.0.0.RELEASE.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-starter-json-2.0.0.RELEASE.jar;%APP_LIB_DIR%\com.fasterxml.jackson.datatype.jackson-datatype-jdk8-2.9.4.jar;%APP_LIB_DIR%\com.fasterxml.jackson.datatype.jackson-datatype-jsr310-2.9.4.jar;%APP_LIB_DIR%\com.fasterxml.jackson.module.jackson-module-parameter-names-2.9.4.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-starter-tomcat-2.0.0.RELEASE.jar;%APP_LIB_DIR%\org.apache.tomcat.embed.tomcat-embed-core-8.5.28.jar;%APP_LIB_DIR%\org.apache.tomcat.embed.tomcat-embed-el-8.5.28.jar;%APP_LIB_DIR%\org.apache.tomcat.embed.tomcat-embed-websocket-8.5.28.jar;%APP_LIB_DIR%\org.hibernate.validator.hibernate-validator-6.0.7.Final.jar;%APP_LIB_DIR%\javax.validation.validation-api-2.0.1.Final.jar;%APP_LIB_DIR%\org.jboss.logging.jboss-logging-3.3.0.Final.jar;%APP_LIB_DIR%\com.fasterxml.classmate-1.3.1.jar;%APP_LIB_DIR%\org.springframework.spring-webmvc-5.0.4.RELEASE.jar;%APP_LIB_DIR%\org.springframework.security.spring-security-rsa-1.0.5.RELEASE.jar;%APP_LIB_DIR%\org.bouncycastle.bcpkix-jdk15on-1.56.jar;%APP_LIB_DIR%\org.bouncycastle.bcprov-jdk15on-1.56.jar;%APP_LIB_DIR%\org.eclipse.jgit.org.eclipse.jgit-4.8.0.201706111038-r.jar;%APP_LIB_DIR%\com.jcraft.jsch-0.1.54.jar;%APP_LIB_DIR%\com.googlecode.javaewah.JavaEWAH-1.1.6.jar;%APP_LIB_DIR%\org.yaml.snakeyaml-1.19.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-starter-test-2.0.1.RELEASE.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-starter-2.0.1.RELEASE.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-2.0.1.RELEASE.jar;%APP_LIB_DIR%\org.springframework.spring-core-5.0.5.RELEASE.jar;%APP_LIB_DIR%\org.springframework.spring-jcl-5.0.5.RELEASE.jar;%APP_LIB_DIR%\org.springframework.spring-context-5.0.5.RELEASE.jar;%APP_LIB_DIR%\org.springframework.spring-aop-5.0.5.RELEASE.jar;%APP_LIB_DIR%\org.springframework.spring-beans-5.0.5.RELEASE.jar;%APP_LIB_DIR%\org.springframework.spring-expression-5.0.5.RELEASE.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-autoconfigure-2.0.1.RELEASE.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-starter-logging-2.0.1.RELEASE.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-test-2.0.1.RELEASE.jar;%APP_LIB_DIR%\org.springframework.boot.spring-boot-test-autoconfigure-2.0.1.RELEASE.jar;%APP_LIB_DIR%\com.jayway.jsonpath.json-path-2.4.0.jar;%APP_LIB_DIR%\net.minidev.json-smart-2.3.jar;%APP_LIB_DIR%\net.minidev.accessors-smart-1.2.jar;%APP_LIB_DIR%\org.ow2.asm.asm-5.0.4.jar;%APP_LIB_DIR%\junit.junit-4.12.jar;%APP_LIB_DIR%\org.hamcrest.hamcrest-core-1.3.jar;%APP_LIB_DIR%\org.assertj.assertj-core-3.9.1.jar;%APP_LIB_DIR%\org.mockito.mockito-core-2.15.0.jar;%APP_LIB_DIR%\net.bytebuddy.byte-buddy-1.7.9.jar;%APP_LIB_DIR%\net.bytebuddy.byte-buddy-agent-1.7.9.jar;%APP_LIB_DIR%\org.objenesis.objenesis-2.6.jar;%APP_LIB_DIR%\org.hamcrest.hamcrest-library-1.3.jar;%APP_LIB_DIR%\org.skyscreamer.jsonassert-1.5.0.jar;%APP_LIB_DIR%\com.vaadin.external.google.android-json-0.0.20131108.vaadin1.jar;%APP_LIB_DIR%\org.springframework.spring-test-5.0.5.RELEASE.jar;%APP_LIB_DIR%\org.xmlunit.xmlunit-core-2.5.1.jar"
set "APP_MAIN_CLASS=io.skysail.service.config.ConfigServiceApplication"

if defined CUSTOM_MAIN_CLASS (
    set MAIN_CLASS=!CUSTOM_MAIN_CLASS!
) else (
    set MAIN_CLASS=!APP_MAIN_CLASS!
)

rem Call the application and pass all arguments unchanged.
"%_JAVACMD%" !_JAVA_OPTS! !SKYSAIL_SERVICES_CONFIG_OPTS! -cp "%APP_CLASSPATH%" %MAIN_CLASS% !_APP_ARGS!

@endlocal


:end

exit /B %ERRORLEVEL%
