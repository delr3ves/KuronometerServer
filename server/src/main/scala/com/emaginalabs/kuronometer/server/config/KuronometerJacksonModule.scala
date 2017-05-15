package com.emaginalabs.kuronometer.server.config

import com.emaginalabs.kuronometer.core.model.{BuildTool, Platform}
import com.fasterxml.jackson.core.{JsonGenerator, JsonParser}
import com.fasterxml.jackson.databind._
import com.twitter.finatra.json.modules.FinatraJacksonModule

object KuronometerJacksonModule extends FinatraJacksonModule {
  override protected val propertyNamingStrategy: PropertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE

  override val additionalJacksonModules = Seq(
    new com.fasterxml.jackson.databind.module.SimpleModule {
      addDeserializer(classOf[Platform], PlatformDeserializer)
      addSerializer(classOf[Platform], PlatformSerializer)

      addDeserializer(classOf[BuildTool], BuildToolDeserializer)
      addSerializer(classOf[BuildTool], BuildToolSerializer)
    })
}

object PlatformDeserializer extends JsonDeserializer[Platform] {
  override def deserialize(p: JsonParser, ctxt: DeserializationContext): Platform = Platform(p.getValueAsString)
}

object PlatformSerializer extends JsonSerializer[Platform] {
  override def serialize(value: Platform, gen: JsonGenerator, serializers: SerializerProvider): Unit = gen.writeString(value.name)
}

object BuildToolDeserializer extends JsonDeserializer[BuildTool] {
  override def deserialize(p: JsonParser, ctxt: DeserializationContext): BuildTool = BuildTool(p.getValueAsString)
}

object BuildToolSerializer extends JsonSerializer[BuildTool] {
  override def serialize(value: BuildTool, gen: JsonGenerator, serializers: SerializerProvider): Unit = gen.writeString(value.name)
}
