package com.emaginalabs.kuronometer.server

import com.emaginalabs.kuronometer.server.resources.MeasurementeControllerComponent
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter


class KuronometerServer extends HttpServer
  with MeasurementeControllerComponent.Default {

  override val defaultFinatraHttpPort: String = ":8080"

  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add(measurementController)
  }
}
