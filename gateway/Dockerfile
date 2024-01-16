FROM maven:3.9.2-eclipse-temurin-17-alpine as builder
ARG WORKDIR
WORKDIR /${WORKDIR}
COPY /${WORKDIR}/src /${WORKDIR}/src
COPY /${WORKDIR}/pom.xml /${WORKDIR}
COPY /${WORKDIR}/status.wsdl /${WORKDIR}
RUN mvn clean install

FROM eclipse-temurin:17-alpine as server
RUN apk --no-cache add curl
ARG WORKDIR
ARG JAR_FILE=/${WORKDIR}/target/*.jar
COPY --from=builder ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]