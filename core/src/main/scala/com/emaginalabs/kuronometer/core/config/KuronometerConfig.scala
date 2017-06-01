package com.emaginalabs.kuronometer.core.config

case class KuronometerConfig(esUrl: String, esUser: String, esPass: String)

object ESConfig {
  val SchemaVersion = "v1"
  val ReportIndex = s"reports-${SchemaVersion}/buildExecution/"
}