# User Registry

This application is a user registry. It's used to register new users and view registered users details.

## Project Structure

In the project root, JHipster generates configuration files for tools like git and maven files pom.xml mvnw and mvnw.cmd.

`/src/*` structure follows default Java structure.


- `/src/main/docker` - Docker configurations for the application and services that the application depends on

## Development

To start your application in the dev, run:

```
./mvnw
```


## Testing

### Spring Boot tests

To launch your application's unit tests and integration tests, run:

```
./mvnw verify
```

### Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a mongodb database in a docker container, run:

```
docker compose -f src/main/docker/mongodb.yml up -d
```

To stop it and remove the container, run:

```
docker compose -f src/main/docker/mongodb.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
npm run java:docker
```

Or build a arm64 docker image when using an arm64 processor os like MacOS with M1 processor family running:

```
npm run java:docker:arm64
```

Then run:

```
docker compose -f src/main/docker/app.yml up -d
```

When running Docker Desktop on MacOS Big Sur or later, consider enabling experimental `Use the new Virtualization framework` for better processing performance ([disk access performance is worse](https://github.com/docker/roadmap/issues/7)).

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Using Docker to start MongoDB Database

To start a mongodb database in a docker container, run:

```
docker compose -f src/main/docker/mongodb.yml up -d
```

To stop it and remove the container, run:

```
docker compose -f src/main/docker/mongodb.yml down
```


## UML Diagrams

### Entities

![alt text](https://i.ibb.co/kDB5jSL/entities.png)

### Services

![alt text](https://i.ibb.co/9pv7S5Z/services.png)

### Resources (REST Controllers)

![alt text](https://i.ibb.co/0YVTTVp/User-Resouce.png)

### DTOs (Data Transfer Objects)

![alt text](https://i.ibb.co/hMMTLwz/dtos.png)

### Mappers

![alt text](https://i.ibb.co/qsy6bTT/mappers.png)

### REST Exception Handler

![alt text](https://i.ibb.co/RcfGywf/Rest-Exception-Handler.png)
