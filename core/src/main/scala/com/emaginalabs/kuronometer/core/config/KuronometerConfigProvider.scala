package com.emaginalabs.kuronometer.core.config

import scala.util.Properties.envOrElse

class KuronometerConfigProvider {
  def apply(): KuronometerConfig = {
    KuronometerConfig(
      esUrl = envOrElse("ELASTIC_URL", "http://localhost:9200/"),
      esUser = envOrElse("ELASTIC_USER", "elastic"),
      esPass = envOrElse("ELASTIC_PASS", "changeme")
    )
  }

}

trait KuronometerConfigProviderComponent {

  val kuronometerConfigProvider: KuronometerConfigProvider
}

object KuronometerConfigProviderComponent {

  trait Default extends KuronometerConfigProviderComponent {
    override lazy val kuronometerConfigProvider = new KuronometerConfigProvider
  }
}
