FROM openjdk:17-jdk-alpine
MAINTAINER Kirill Pivovarov
COPY build/libs/pasteboxTestTask-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java", "-jar", "/pastebox.jar"]