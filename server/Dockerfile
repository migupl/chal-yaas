FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY build/libs/*.jar server.jar

RUN unzip server.jar && rm server.jar

EXPOSE 8080
CMD java  -noverify ${JAVA_OPTS}  org.springframework.boot.loader.JarLauncher