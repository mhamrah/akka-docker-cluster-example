import DockerKeys._
import sbtdocker.{ Dockerfile, ImageName}
import com.typesafe.sbt.packager.Keys._

name := "clustering"

organization := "com.mlh"

version := "0.1.0-SNAPSHOT"

homepage := Some(url("https://github.com/mhamrah/clustering"))

startYear := Some(2013)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/mhamrah/clustering"),
    "scm:git:https://github.com/mhamrah/clustering.git",
    Some("scm:git:git@github.com:mhamrah/clustering.git")
  )
)

/* scala versions and options */
scalaVersion := "2.11.1"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  ,"-unchecked"
  ,"-encoding", "UTF-8"
  ,"-Xlint"
  // "-optimise"   // this option will slow your build
)

scalacOptions ++= Seq(
  "-Yclosure-elim",
  "-Yinline"
)

// These language flags will be used only for 2.10.x.
// Uncomment those you need, or if you hate SIP-18, all of them.
scalacOptions <++= scalaVersion map { sv =>
  if (sv startsWith "2.11") List(
    "-Xverify"
    ,"-feature"
    ,"-language:postfixOps"
  )
  else Nil
}

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

val akka = "2.3.3"

/* dependencies */
libraryDependencies ++= Seq (
  "com.github.nscala-time" %% "nscala-time" % "1.2.0"
  // -- testing --
  , "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"
  // -- Logging --
  ,"ch.qos.logback" % "logback-classic" % "1.1.1"
  // -- Akka --
  ,"com.typesafe.akka" %% "akka-testkit" % akka % "test"
  ,"com.typesafe.akka" %% "akka-actor" % akka
  ,"com.typesafe.akka" %% "akka-slf4j" % akka
  ,"com.typesafe.akka" %% "akka-cluster" % akka
  // -- json --
  ,"org.json4s" %% "json4s-jackson" % "3.2.10"
  // -- config --
  ,"com.typesafe" % "config" % "1.2.0"
)

/* you may need these repos */
resolvers ++= Seq(
  // Resolver.sonatypeRepo("snapshots")
  // Resolver.typesafeRepo("releases")
  //"spray repo" at "http://repo.spray.io"
)

packageArchetype.java_server

seq(Revolver.settings: _*)

sbtdocker.Plugin.dockerSettings

mappings in Universal += baseDirectory.value / "docker" / "start" -> "bin/start"

docker <<= docker.dependsOn(com.typesafe.sbt.packager.universal.Keys.stage.in(Compile))

// Define a Dockerfile
dockerfile in docker <<= (name, stagingDirectory in Universal) map {
  case (appName, stageDir) =>
    val workingDir = s"/opt/${appName}"
    new Dockerfile {
      // Use a base image that contain Java
      from("relateiq/oracle-java8")
      maintainer("Michael Hamrah <m@hamrah.com>")
      expose(1600)
      add(stageDir, workingDir)
      run("chmod",  "+x",  s"/opt/${appName}/bin/${appName}")
      run("chmod",  "+x",  s"/opt/${appName}/bin/start")
      workDir(workingDir)
      entryPointShell(s"bin/start", appName, "$@")
    }
}

imageName in docker := {
  ImageName(
    namespace = Some("hamrah.com"),
    repository = name.value
    //,tag = Some("v" + version.value))
  )
}
