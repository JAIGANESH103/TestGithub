# syntax=docker/dockerfile:experimental

########Maven build stage########
FROM maven:3.6.1-jdk-8 as maven_build
WORKDIR /app

#copy pom
COPY ./pom.xml /app

#copy source
COPY src ./src

# build the app (no dependency download here)
RUN target=/root/.m2  mvn clean package -Dmaven.test.skip

# split the built app into multiple layers to improve layer rebuild
RUN mkdir -p target/docker-packaging && cd target/docker-packaging && jar -xf ../*.war

########JRE run stage########
FROM openjdk:8-jdk-alpine
WORKDIR /app

#copy built app layer by layer
ARG DOCKER_PACKAGING_DIR=target/docker-packaging



#run the app
CMD java -cp .:classes:lib/* \
         -Djava.security.egd=file:/dev/./urandom \
         foo.bar.MySpringBootApplication