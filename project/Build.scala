import sbt.Keys._
import sbt._
import sbtassembly.AssemblyPlugin.autoImport._

object Build extends sbt.Build {

  def baseModule(id: String): Project =
    Project(id = id, base = file(id))
      .configs(IntegrationTest extend Test)
      .settings(Defaults.itSettings: _*)

  def subModule(id: String): Project =
    baseModule(id).disablePlugins(sbtassembly.AssemblyPlugin)

  def subModuleWithAssembly(id: String): Project =
    baseModule(id).settings(
      libraryDependencies ++= Dependencies.Test,
      publishArtifact := false,
      assemblyJarName in assembly := s"${name.value}.jar"
    )

  lazy val root = Project(id = "kuronometer", base = file(".")).aggregate(
    core,
    server
  )

  lazy val core = subModule("core")
  lazy val server = subModuleWithAssembly("server").dependsOn(core)
}