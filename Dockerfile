FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD target/leeservice-1.0.0.jar leeservice.jar
ENTRYPOINT ["java","-jar","/leeservice.jar"]
