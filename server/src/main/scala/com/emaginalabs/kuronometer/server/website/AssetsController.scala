package com.emaginalabs.kuronometer.server.website

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class AssetsController extends Controller {

  get("/assets/:*") { request: Request =>
    response.ok.file(s"/assets/${request.params("*")}")
  }
}
