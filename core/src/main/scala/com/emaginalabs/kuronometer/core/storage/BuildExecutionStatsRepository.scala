package com.emaginalabs.kuronometer.core.storage

import com.emaginalabs.kuronometer.core.config.{ESConfig, KuronometerConfig, KuronometerConfigProviderComponent}
import com.emaginalabs.kuronometer.core.model.BuildExecutionReportStats
import play.api.libs.ws.{StandaloneWSClient, StandaloneWSResponse, WSAuthScheme}

import scala.concurrent.Future
import scala.concurrent.duration.DurationLong
import scala.concurrent.ExecutionContext.Implicits.global

class BuildExecutionStatsRepository(client: StandaloneWSClient, config: KuronometerConfig) {

  private def statsUrl =
    s"${config.esUrl}${ESConfig.ReportIndex}_search"


  def globalStats(): Future[BuildExecutionReportStats] = {
    val query =
      """{
        |    "aggs" : {
        |        "totalTime" : { "sum" : { "field" : "buildStagesExecution.stages.execution.executionTimeInNanoseconds" } },
        |        "avgTime" : { "avg" : { "field" : "buildStagesExecution.stages.execution.executionTimeInNanoseconds" } },
        |        "percentilesTime" : { "percentiles" : { "field" : "buildStagesExecution.stages.execution.executionTimeInNanoseconds" } }
        |    },
        |    "size": 0
        |}""".stripMargin
    client
      .url(statsUrl)
      .withAuth(config.esUser, config.esPass, WSAuthScheme.BASIC)
      .addHttpHeaders(("Content-Type" -> "application/json"))
      .post(query)
      .map(parseStats)
  }

  private def parseStats(response: StandaloneWSResponse): BuildExecutionReportStats = {
    val aggregations = response.json \ "aggregations"
    val totalTime = aggregations.get \ "totalTime"
    val duration = (totalTime.get \ "value").get.as[Long]
    new BuildExecutionReportStats(totalTime = duration nanoseconds)
  }
}

trait BuildExecutionStatsRepositoryComponent {

  val statsRepository: BuildExecutionStatsRepository
}

object BuildExecutionStatsRepositoryComponent {

  trait Default extends BuildExecutionStatsRepositoryComponent {
    this: KuronometerConfigProviderComponent with WSClientComponent =>

    override lazy val statsRepository =
      new BuildExecutionStatsRepository(wSClient, kuronometerConfigProvider())
  }

}
