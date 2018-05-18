# Dockerfile

# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine

MAINTAINER  Ariel Alcocer <ariela@email.com>

WORKDIR /

# Copyng generated Jar file to root directory
ADD target/minimum-*.jar minimum.jar

# Running Jar file
CMD java -jar minimum.jar
