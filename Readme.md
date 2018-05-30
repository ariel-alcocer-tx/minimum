#Minimum

### Iteration 1: Run a single Jar file
Including steps for building and running a docker image for a Java Application that is maven based.
  * Build the docker image using Dockerfile configuration
  ``` dockerfile
  # Dockerfile
  
  # Base Alpine Linux based image with OpenJDK JRE only
  FROM openjdk:8-jre-alpine
  
  MAINTAINER  Ariel Alcocer <ariela@email.com>
  
  WORKDIR /
  
  # Copyng generated Jar file to root directory
  ADD target/minimum-*.jar minimum.jar
  
  # Running Jar file
  CMD java -jar minimum.jar
   ```
  * Build the docker image
   ```
    docker build -t tn/minimum:latest .
   ```
  * Run the created docker image
   ```
    docker run tn/minimum:latest
   ```

Tested on different CI Servers.
-------------------------------
test1.
