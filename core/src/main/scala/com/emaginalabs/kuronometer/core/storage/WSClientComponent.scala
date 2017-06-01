package com.emaginalabs.kuronometer.core.storage

import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.ahc.StandaloneAhcWSClient

trait WSClientComponent {
  val wSClient: StandaloneWSClient
}

object WSClientComponent {
  trait Default extends WSClientComponent {
    import akka.actor.ActorSystem
    import akka.stream.ActorMaterializer

    private implicit val system = ActorSystem()
    private implicit val materializer = ActorMaterializer()

    override lazy val wSClient: StandaloneWSClient = StandaloneAhcWSClient()
  }
}
