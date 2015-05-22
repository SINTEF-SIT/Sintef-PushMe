name := "PushMe"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.34",
  "com.typesafe.play.plugins" %% "play-plugins-mailer" % "2.3.1"
)     

play.Project.playJavaSettings

