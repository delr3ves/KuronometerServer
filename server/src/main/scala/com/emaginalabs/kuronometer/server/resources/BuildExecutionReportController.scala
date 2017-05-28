package com.emaginalabs.kuronometer.server.resources

import javax.inject.Inject

import com.emaginalabs.kuronometer.core.model.BuildExecutionReport
import com.jakehschwartz.finatra.swagger.SwaggerController
import io.swagger.models.Swagger

class BuildExecutionReportController @Inject()(s: Swagger) extends SwaggerController {

  implicit protected val swagger = s

  postWithDoc("/buildExecution") { o =>
    o.summary("Stores the build execution report")
      .tag("BuidExecutionReport")
      .bodyParam[BuildExecutionReport]("The report to be stored")
      .responseWith[BuildExecutionReport](200, "The stored report")
  } { report: BuildExecutionReport =>
    report
  }
}