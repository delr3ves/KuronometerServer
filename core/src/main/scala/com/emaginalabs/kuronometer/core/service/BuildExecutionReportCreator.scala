package com.emaginalabs.kuronometer.core.service

import com.emaginalabs.kuronometer.core.model.BuildExecutionReport
import com.emaginalabs.kuronometer.core.storage.{
  BuildExecutionReportRepository,
  BuildExecutionReportRepositoryComponent
}

import scala.concurrent.Future

class BuildExecutionReportCreator(repository: BuildExecutionReportRepository) {

  def apply(report: BuildExecutionReport): Future[BuildExecutionReport] = repository.add(report)
}

trait BuildExecutionReportCreatorComponent {

  val reportCreator: BuildExecutionReportCreator

}

object BuildExecutionReportCreatorComponent {

  trait Default extends BuildExecutionReportCreatorComponent {
    this: BuildExecutionReportRepositoryComponent =>

    override lazy val reportCreator = new BuildExecutionReportCreator(reportRepository)
  }

}
