name := "tapir"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.tapir" %% "tapir-core" % "0.18.0-M10"
)
scalacOptions += "-Ypartial-unification"