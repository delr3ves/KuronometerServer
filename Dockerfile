FROM  java:openjdk-8-jre-alpine

MAINTAINER  Sergio Arroyo  <sergio.arroyo.cuevas@gmail.com>

ADD server/target/scala-2.12/kuronometer-server.jar /app/

ENV ELASTIC_URL ""
ENV ELASTIC_USER ""
ENV ELASTIC_PASS ""

WORKDIR /app

CMD java -jar /app/kuronometer-server.jar
