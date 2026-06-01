val scala3Version = "3.8.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "pennies",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.3.0" % Test,
    libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.10",
    libraryDependencies += "com.softwaremill.sttp.client4" %% "core" % "4.0.0",
    libraryDependencies += "com.lihaoyi" %% "upickle" % "3.3.1"

  )
