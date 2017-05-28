name := "kuronometer-server"

version := "1.0"

organization in ThisBuild := "com.emaginalabs.kuronometer.server"

scalaVersion in ThisBuild := "2.12.2"

releaseVersionBump := sbtrelease.Version.Bump.Minor

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion
)

addCommandAlias("test-all", ";test ;it:test")

import org.scoverage.coveralls.Imports.CoverallsKeys._
import scala.util.Properties.envOrNone

coverallsToken := envOrNone("COVERALLS_TOKEN")
