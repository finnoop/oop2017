name := "OOP 2017"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)

enablePlugins(JmhPlugin)
