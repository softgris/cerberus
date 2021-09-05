FROM gradle:7.2.0-jdk11 AS build
ARG HOME=/usr/local/cerberus
COPY src ${HOME}/src
COPY build.gradle ${HOME}
WORKDIR ${HOME}
RUN gradle build -x test

FROM openjdk:11-jre-slim
ARG HOME=/usr/local/cerberus
COPY --from=build ${HOME}/build/libs/*.jar ${HOME}/cerberus.jar
COPY --from=build ${HOME}/src/main/resources ${HOME}/resources
WORKDIR ${HOME}
VOLUME /tmp
ENTRYPOINT ["java", "-jar", "cerberus.jar"]