import pl.project13.scala.sbt.JmhPlugin

name := "OOP 2017"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "com.github.tototoshi" %% "scala-csv" % "1.3.3",
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.3-1",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3-1"
)

enablePlugins(JmhPlugin)
