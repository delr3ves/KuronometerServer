name := "kuronometer-core"

libraryDependencies ++= Seq(
  "io.circe"          %% "circe-core"    % Dependencies.CirceVersion,
  "io.circe"          %% "circe-generic" % Dependencies.CirceVersion,
  "io.circe"          %% "circe-parser"  % Dependencies.CirceVersion,
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.0.0-RC2"
)
