package com.emaginalabs.kuronometer.core.config

import org.scalatest.{FlatSpec, Matchers}

class KuronometerConfigProviderTest extends FlatSpec with Matchers with KuronometerConfigProviderComponent.Default {

  "KuronometerConfigProvider" should "default values when no environment is defined" in {
    kuronometerConfigProvider() shouldBe KuronometerConfig(
      esUrl = "http://elastic:9200/",
      esUser = "elastic",
      esPass = "changeme")
  }
}
