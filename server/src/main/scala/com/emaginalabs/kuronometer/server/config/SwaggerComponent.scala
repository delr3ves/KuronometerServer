package com.emaginalabs.kuronometer.server.config

import javax.inject.Singleton

import com.google.inject.Provides
import com.jakehschwartz.finatra.swagger.SwaggerModule
import io.swagger.models.{Info, Swagger}

object KuronometerSwaggerModule extends SwaggerModule {

  @Provides
  @Singleton
  def provideSwagger: Swagger =
    new Swagger().info(
      new Info()
        .description("The Kuronometer Server API.")
        .version("1.0.0")
        .title("Kuronometer Server API"))

}
