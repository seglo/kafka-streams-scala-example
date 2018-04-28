import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    resolvers += Resolver.mavenLocal,
    name := "kafka-streams-scala-example",
    libraryDependencies ++= Seq(
      "org.apache.kafka" %% "kafka-streams-scala" % "2.0.0-SNAPSHOT",
      scalaTest % Test
    )
  )
