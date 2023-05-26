FROM gradle:7.6-alpine as BUILDER
WORKDIR /opt/app
COPY . .
RUN gradle build

FROM openjdk:17-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/build/libs/*.jar ./app.jar
CMD [ "java", "-jar", "/opt/app/app.jar" ]