FROM openjdk:17-jdk-alpine
MAINTAINER minhle.com
COPY target/leeservice-1.0.0.jar leeservice-1.0.0.jar
ENTRYPOINT ["java","-jar","/leeservice-1.0.0.jar"]
