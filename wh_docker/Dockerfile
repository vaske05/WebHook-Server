#Maven build
FROM maven:3.6.3-amazoncorretto-11 AS build
WORKDIR /build
COPY /pom.xml .
RUN mvn dependency:go-offline

COPY /src /build/src/
RUN mvn clean install -Dmaven.test.skip=true
LABEL tag=whserver

FROM openjdk:11
ADD target/wh-server.war whserver.war
EXPOSE 8001 8081
ENTRYPOINT ["java", "-jar" , "whserver.war"]
