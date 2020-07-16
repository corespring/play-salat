import sbt._
import sbt.Keys._

import org.corespring.sbt.repo.RepoAuthPlugin.Keys._


object ProjectBuild extends Build {

  lazy val buildVersion =  "1.6.0"

  lazy val root = Project(id = "play-plugins-salat", base = file("."), settings = Project.defaultSettings).settings(
    publishTo := authPublishTo.value,
    organization := "org.corespring",
    description := "MongoDB Salat plugin for PlayFramework 2",
    version := buildVersion,
    scalaVersion := "2.10.5",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    parallelExecution in Test := false,
    testFrameworks += TestFrameworks.Specs2,
    (javacOptions in Compile) ++= Seq("-source", "1.7", "-target", "1.7"),
//    (javacOptions in Compile) ++= Seq("-source", "1.7", "-target", "1.7"),
    resolvers ++= Seq(
      "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
      "play Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/",
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")
    ),

    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % "2.2.6" % "provided",
      "com.typesafe.play" % "play-exceptions" % "2.2.6" % "provided",
      "com.typesafe.play" %% "play-test" % "2.2.6" % "test",
      "com.github.salat" %% "salat" % "1.10.0",
      "org.mongodb" %% "casbah" % "2.8.2"
    )
    
  )
}

//object Publish {
//  lazy val settings = Seq(
//    publishMavenStyle := true,
//    publishTo <<= version { (v: String) =>
//      val nexus = "https://oss.sonatype.org/"
//      if (v.trim.endsWith("SNAPSHOT"))
//        Some("sonatype snapshots" at nexus + "content/repositories/snapshots")
//      else
//        Some("sonatype releases"  at nexus + "service/local/staging/deploy/maven2")
//    },
//    publishArtifact in Test := false,
//    pomIncludeRepository := { _ => false },
//    licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
//    homepage := Some(url("https://github.com/corespring/play-salat")),
//    pomExtra := (
//      <scm>
//        <url>git://github.com/corespring/play-salat.git</url>
//        <connection>scm:git://github.com/corespring/play-salat.git</connection>
//      </scm>
//      <developers>
//        <developer>
//          <id>edeustace</id>
//          <name>Ed Eustace</name>
//          <url>http://github.com/corespring</url>
//        </developer>
//      </developers>)
//  )
//}
