name := "SlickExample2"

version := "0.1"

scalaVersion := "2.13.5"
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "org.postgresql" % "postgresql" % "42.2.10",
  "com.github.tminglei" %% "slick-pg_core" % "0.18.1"
)
