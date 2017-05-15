package com.emaginalabs.kuronometer.server

import com.emaginalabs.kuronometer.server.config.KuronometerJacksonModule
import com.emaginalabs.kuronometer.server.resources.BuildExecutionReportControllerComponent
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

class KuronometerServer extends HttpServer
  with BuildExecutionReportControllerComponent.Default {

  override val defaultFinatraHttpPort: String = ":8080"

  override def jacksonModule = KuronometerJacksonModule

  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add(buildExecutionController)
  }
}
