package com.emaginalabs.kuronometer.server.resources

import com.emaginalabs.kuronometer.core.model.BuildExecutionReport
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class BuildExecutionReportController extends Controller {

  get("/buildExecution") { request: Request =>
    "<h1>I'm Kuronometer</h1>"
  }

  post("/buildExecution") { report: BuildExecutionReport =>
    report
  }
}

trait BuildExecutionReportControllerComponent {

  def buildExecutionController: BuildExecutionReportController
}

object BuildExecutionReportControllerComponent {

  trait Default extends BuildExecutionReportControllerComponent {
    override lazy val buildExecutionController: BuildExecutionReportController =
      new BuildExecutionReportController
  }

}
