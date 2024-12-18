FROM openjdk:17.0.2-bullseye

ARG JAR_FILE=target/bci-0.0.1-SNAPSHOT.jar

## Copy application jar
COPY ${JAR_FILE} bci.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bci.jar"]


## Folders
#RUN mkdir -p /application/bin
#
## Copy application jar
#COPY /build/libs/server.jar /application/bin/server.jar
#
#ENV JAR=/application/bin/server.jar
#
## Start command
#CMD ["/bin/bash", "-c", "java ${JAVA_OPTS} -jar ${JAR} ${JAR_OPTS}"]