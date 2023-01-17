FROM maven:3.8.6-jdk-11 as build-stage

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package



# ---



FROM openjdk:11

WORKDIR /usr/src/coordinates

COPY --from=build-stage /usr/src/app/target/coordinates*.jar /usr/src/coordinates


RUN cd /usr/src/coordinates && \
    mkdir logs

EXPOSE 8061

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5085 \
 -Xms256m -Xmx1024m \
 -jar coordinates*.jar
