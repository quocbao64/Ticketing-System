# build stage
FROM maven:3-openjdk-17 as build

ENV HOME=/app

WORKDIR $HOME

COPY ./ $HOME

RUN --mount=type=cache,target=/root/.m2 mvn -f $HOME/pom.xml -DskipTests clean package

# docker build stage
FROM openjdk:17-alpine

COPY --from=build /app/discovery-server/target/*.jar discovery.jar

ENV PORT=8761
EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "discovery.jar"]
