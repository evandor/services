/*
  General Scala attributes
 */
scalaVersion := "2.11.12"

/*
  General project attributes
 */
organization := "io.skysail"
name := "skysail-services-config"
version := "0.1"
maintainer := "Carsten Gräf <evandor@gmail.com>"
description := "to be done"
organizationHomepage := Some(url("http://www.skysail.io"))

resolvers += "SpringMilestones" at "https://repo.spring.io/libs-milestone"

/*
  Project dependencies
 */
libraryDependencies ++= Seq(
  "org.springframework.cloud" % "spring-cloud-config-server" % "2.0.0.M9",
  "org.springframework.boot" % "spring-boot-starter-test" % "2.0.1.RELEASE"
  //"org.springframework.boot" % "spring-boot-starter-web" % "1.5.4.RELEASE",
  //"org.springframework.boot" % "spring-boot-configuration-processor" % "1.5.4.RELEASE"
)

/*
  Packaging plugin
 */

// enable the Java app packaging archetype and Ash script (for Alpine Linux, doesn't have Bash)
enablePlugins(JavaAppPackaging, AshScriptPlugin)

// set the main entrypoint to the application that is used in startup scripts
mainClass in Compile := Some("io.skysail.service.config.ConfigServiceApplication")

// the Docker image to base on (alpine is smaller than the debian based one (120 vs 650 MB)
dockerBaseImage := "openjdk:8-jre-alpine"

// creates tag 'latest' as well when publishing
dockerUpdateLatest := true