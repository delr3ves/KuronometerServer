package com.emaginalabs.kuronometer.server.website

import javax.inject.Inject

import com.emaginalabs.kuronometer.core.storage.BuildExecutionStatsRepository
import com.twitter.bijection.Conversion.asMethod
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.response.Mustache

import scala.concurrent.ExecutionContext.Implicits.global
import com.twitter.util.{Future => TwitterFuture}

import com.twitter.bijection.twitter_util.UtilBijections._

class IndexController @Inject()(statsRepository: BuildExecutionStatsRepository) extends Controller {

  @Mustache("index")
  case class IndexView(timeCompiling: Long)

  get("/") { request: Request =>
    statsRepository.globalStats().map(stats => IndexView(stats.totalTime.toSeconds)).as[TwitterFuture[IndexView]]
  }
}
