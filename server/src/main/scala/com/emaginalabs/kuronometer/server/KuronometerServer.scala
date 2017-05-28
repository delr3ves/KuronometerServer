package com.emaginalabs.kuronometer.server

import com.emaginalabs.kuronometer.server.config.{KuronometerJacksonModule, KuronometerSwaggerModule, SwaggerComponent}
import com.emaginalabs.kuronometer.server.resources.{BuildExecutionReportController, BuildExecutionReportControllerComponent}
import com.jakehschwartz.finatra.swagger.DocsController
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

class KuronometerServer extends HttpServer {

  override protected def modules = Seq(KuronometerSwaggerModule)

  override val defaultFinatraHttpPort: String = ":8080"

  override def jacksonModule = KuronometerJacksonModule

  override protected def configureHttp(router: HttpRouter): Unit =
    router
      .add[DocsController]
      .add[BuildExecutionReportController]
}
