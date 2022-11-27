# location-service

### Setup

 - Download Amazon Corretto 17 from: Amazon Corretto webpage
 - Install Amazon Corretto 17

### Database

Run PostGIS Docker image 
- Install Docker
- Pull the latest PostGIS Docker image

```sh
docker pull postgis/postgis
```
- Run the Docker image locally and make PostGIS listen on port 5432:
```sh
docker run -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgis/postgis
```

### Build the project

```sh
./gradlew build
```

The build and all tests should succeed.

In case of error: invalid source release: 17
```sh
export JAVA_HOME="path/to/Java17"
```

If the build fails, try to clean it first:

```sh
./gradlew clean
```

### Running the application locally

```sh
./gradlew run
```

Will get it executing on localhost:8080 by default.

### Running the service and database with docker compose

After building the project
```sh
docker compose up
```
It will start database and service.

### OpenAPI documentation

It can be found in the [repository](src/main/resources/docs/openapi.yaml)

### Future Work
- Missing tests
- Integration of JaCoCo
- Authentication and Authorization with JWT
- Enabling profiles for different environments e.g. development, staging, production
- Adding CircleCI pipeline


