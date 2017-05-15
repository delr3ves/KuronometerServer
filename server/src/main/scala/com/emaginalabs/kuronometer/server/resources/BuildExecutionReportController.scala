package com.emaginalabs.kuronometer.server.resources

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class MeasurementController extends Controller {

  get("/measure") { request: Request =>
    "<h1>I'm Kuronometer</h1>"
  }

  post("/measure") { request: Request =>
    ""
  }
}

trait MeasurementeControllerComponent {

  def measurementController: MeasurementController
}

object MeasurementeControllerComponent {

  trait Default extends MeasurementeControllerComponent {
    override lazy val measurementController: MeasurementController = new MeasurementController
  }

}
