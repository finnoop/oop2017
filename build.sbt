import pl.project13.scala.sbt.JmhPlugin

name := "OOP 2017"

version := "1.0"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)

enablePlugins(JmhPlugin)
