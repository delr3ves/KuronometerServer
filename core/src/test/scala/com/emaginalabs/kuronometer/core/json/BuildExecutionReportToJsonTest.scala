package com.emaginalabs.kuronometer.core.json

import com.emaginalabs.kuronometer.core.model._
import org.scalatest.{FlatSpec, Matchers}
import io.circe.generic.auto._
import io.circe.syntax._

class BuildExecutionReportToJsonTest extends FlatSpec with Matchers {
  "BuildExecutionReport" should "serialize to proper json when anonymous is passed" in {
    val anonymousReport = BuildExecutionReport(
      project = ProjectDefinition(None, None),
      platform = Platform("java"),
      buildTool = BuildTool("sbt"),
      buildStagesExecution = BuildStages(Nil)
    )
    anonymousReport.asJson.as[BuildExecutionReport].right.get shouldBe anonymousReport
  }

  it should "serialize to proper json when named is passed" in {
    val anonymousReport = BuildExecutionReport(
      project = ProjectDefinition(
        info =
          Some(ProjectInformation(name = "SampleProject", description = Some("my super project"))),
        version = Some(ProjectVersion(group = Some("com.mygroup"), version = "1.0.0"))
      ),
      platform = Platform("java"),
      buildTool = BuildTool("sbt"),
      buildStagesExecution = BuildStages(Nil)
    )
    anonymousReport.asJson.as[BuildExecutionReport].right.get shouldBe anonymousReport
  }

}
