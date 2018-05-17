name := "clustering"

organization := "com.mlh"

version := "0.4"

homepage := Some(url("https://github.com/mhamrah/clustering"))

startYear := Some(2013)

/* scala versions and options */
scalaVersion := "2.12.5"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-encoding", "UTF-8",
  "-Xlint",
)

val akka = "2.5.11"

/* dependencies */
libraryDependencies ++= Seq (
  // -- Logging --
  "ch.qos.logback" % "logback-classic" % "1.1.1",
  // -- Akka --
  "com.typesafe.akka" %% "akka-actor"   % akka,
  "com.typesafe.akka" %% "akka-slf4j"   % akka,
  "com.typesafe.akka" %% "akka-cluster" % akka,
  "com.typesafe.akka" %% "akka-testkit" % akka % "test"

)

maintainer := "Michael Hamrah <m@hamrah.com>"

dockerExposedPorts in Docker := Seq(1600)

dockerEntrypoint in Docker := Seq("sh", "-c", "CLUSTER_IP=`/sbin/ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1 }'` bin/clustering $*")

dockerRepository := Some("mhamrah")

dockerBaseImage := "java"
enablePlugins(JavaAppPackaging)
