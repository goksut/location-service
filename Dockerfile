FROM amazoncorretto:17.0.5
COPY ["build/libs/location-service-0.0.1-SNAPSHOT.jar", "app.jar"]
ENTRYPOINT ["java","-jar","/app.jar"]