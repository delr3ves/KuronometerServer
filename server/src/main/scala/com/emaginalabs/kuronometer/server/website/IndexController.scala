package com.emaginalabs.kuronometer.server.website

import javax.inject.Inject

import com.emaginalabs.kuronometer.core.storage.BuildExecutionStatsRepository
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.response.Mustache

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class IndexController @Inject()(statsRepository: BuildExecutionStatsRepository) extends Controller {

  @Mustache("index")
  case class IndexView(timeCompiling: Long)

  get("/") { request: Request =>
    val stats = Await.result(statsRepository.globalStats(), Duration.Inf)
    IndexView(stats.totalTime.toSeconds)
  }
}
