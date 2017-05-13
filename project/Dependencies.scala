import sbt._

object Dependencies {

  val FinatraVersion = "2.10.0"
  val LogbackVersion = "1.2.2"

  val Test = Seq(
    "org.scalatest" %% "scalatest" % "3.0.3" % "test,it",
    "org.scalacheck" %% "scalacheck" % "1.13.5" % "test,it"
  )
}