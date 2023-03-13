FROM openjdk:19-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/holdthese-0.0.1.jar holdthese.jar
ENTRYPOINT ["java","-jar","/holdthese.jar"]