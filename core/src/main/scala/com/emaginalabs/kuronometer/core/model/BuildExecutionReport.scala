package com.emaginalabs.kuronometer.core.model

case class ProjectInformation(name: String, description: Option[String])

case class ProjectVersion(group: Option[String], version: String)

case class ProjectDefinition(info: Option[ProjectInformation], version: Option[ProjectVersion])

case class Platform(name: String)

case class BuildTool(name: String)

case class StageInfo(name: String, description: Option[String], group: Option[String])

case class StageExecution(executionTimeInNanoseconds: Long, timestamp: Long, skipped: Boolean)

case class Stage(info: StageInfo, execution: StageExecution)

case class BuildStages(stages: List[Stage])

case class BuildExecutionReport(
    project: ProjectDefinition,
    platform: Platform,
    buildTool: BuildTool,
    buildStagesExecution: BuildStages
)
