# User Registry

This application is a user registry. It can be used to register new users and show registered users details.

## REST API Services :

### 1. Register a new user

#### Web Service URL (POST Method) :

```
http://localhost:8080/api/users
```

#### Request sample :

```
POST http://localhost:8080/api/users
Content-Type: application/json

{
  "firstName" : "Toto",
  "lastName" : "Lebobo",
  "birthday" : "1985-02-22T12:59:00Z",
  "address" : "1 avenue De Gaulle",
  "postalCode": "06600",
  "country" : "FRANCE",
  "email" : "aaaaa@gmail.fr",
  "phoneNumber" : "0659808596"
}
```

#### Response sample :

```
{
  "id": "66700c1bdd9d8c412653ba2a",
  "firstName" : "Toto",
  "lastName" : "Lebobo",
  "birthday" : "1985-02-22T12:59:00Z",
  "address" : "1 avenue De Gaulle",
  "postalCode": "06600",
  "country" : "FRANCE",
  "email" : "aaaaa@gmail.fr",
  "phoneNumber" : "0659808596"
}
```

### 2. Show details a registered user

#### Web Service URL (GET Method) :

```
http://localhost:8080/api/users/{{id}}
```

#### Request sample :

```
GET http://localhost:8080/api/users/66700c1bdd9d8c412653ba2a
```

#### Response sample :

```
{
  "id": "66700c1bdd9d8c412653ba2a",
  "firstName" : "Toto",
  "lastName" : "Lebobo",
  "birthday" : "1985-02-22T12:59:00Z",
  "address" : "1 avenue De Gaulle",
  "postalCode": "06600",
  "country" : "FRANCE",
  "email" : "aaaaa@gmail.fr",
  "phoneNumber" : "0659808596"
}
```


## Project Structure

In the project root, configuration files for tools like git and maven files pom.xml, mvnw and mvnw.cmd.

- `/src/*` structure follows default Java structure.


- `/src/main/docker` - Docker configurations for the application and services that the application depends on

## Prerequisites

You need to start a MongoDB Database before running the Spring Boot Application User Registry.

### Using Docker to start the MongoDB Database

To start a mongodb database in a docker container, run:

```
docker compose -f src/main/docker/mongodb.yml up -d
```

To stop it and remove the container, run:

```
docker compose -f src/main/docker/mongodb.yml down
```


## Development

To start your application in the dev, run:

```
./mvnw
```


## Testing

### Spring Boot unit tests and Integration tests

To launch your application's unit tests and integration tests, run:

```
./mvnw verify
```

### POSTMAN tests

You can import POSTMAN tests by using the file [UserRegistryAPI.postman_collection.json](UserRegistryAPI.postman_collection.json)

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
