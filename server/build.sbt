name := "kuronometer-server"

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % Dependencies.FinatraVersion,
  "ch.qos.logback" % "logback-classic" % Dependencies.LogbackVersion

)

mainClass in assembly := Some("com.emaginalabs.kuronometer.server.Application")

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.copy(`classifier` = Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)

assemblyMergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case PathList("org", "apache", "commons", "logging", _ *) => MergeStrategy.first
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.first
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case other => (assemblyMergeStrategy in assembly).value(other)
}