package com.emaginalabs.kuronometer.server

import com.emaginalabs.kuronometer.server.config.{KuronometerCoreModule, KuronometerJacksonModule, KuronometerSwaggerModule}
import com.emaginalabs.kuronometer.server.resources.BuildExecutionReportController
import com.emaginalabs.kuronometer.server.website.IndexController
import com.jakehschwartz.finatra.swagger.DocsController
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

class KuronometerServer extends HttpServer {

  override protected def modules = Seq(KuronometerSwaggerModule, KuronometerCoreModule)

  override val defaultFinatraHttpPort: String = ":8080"

  override def jacksonModule = KuronometerJacksonModule

  override protected def configureHttp(router: HttpRouter): Unit =
    router
      .add[DocsController]
      .add[BuildExecutionReportController]
      .add[IndexController]
}
