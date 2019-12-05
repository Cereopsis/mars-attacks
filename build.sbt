
name := "mars-attacks"
version := "1.0"
scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "3.0.8",
    "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

lazy val root = (project in file("."))

scalacOptions in ThisBuild := Seq("-unchecked", "-deprecation", "-Ymacro-annotations")

fork in run := true

