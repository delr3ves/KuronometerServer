package com.emaginalabs.kuronometer.core.storage

import com.emaginalabs.kuronometer.core.config.{ESConfig, KuronometerConfig, KuronometerConfigProviderComponent}
import com.emaginalabs.kuronometer.core.model.BuildExecutionReport
import io.circe.generic.auto._
import io.circe.syntax._
import play.api.libs.ws._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class BuildExecutionReportRepository(client: StandaloneWSClient, config: KuronometerConfig) {

  def add(report: BuildExecutionReport): Future[BuildExecutionReport] = {

    val body = report.asJson.toString
    client
      .url(indexUrl)
      .withAuth(config.esUser, config.esPass, WSAuthScheme.BASIC)
      .addHttpHeaders(("Content-Type" -> "application/json"))
      .post(body)
      .map(_ => report)
  }

  private def indexUrl =
    s"${config.esUrl}${ESConfig.ReportIndex}"
}

trait BuildExecutionReportRepositoryComponent {

  val reportRepository: BuildExecutionReportRepository
}

object BuildExecutionReportRepositoryComponent {

  trait Default extends BuildExecutionReportRepositoryComponent {
    this: KuronometerConfigProviderComponent with WSClientComponent =>

    override lazy val reportRepository =
      new BuildExecutionReportRepository(wSClient, kuronometerConfigProvider())
  }

}
