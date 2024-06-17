# User Registry

This application is a user registry. It's used to register new users and view registered users details.

## REST API Services :

### Register a new user

#### Web Service URL (POST Method) :

```
http://localhost:8080/api/users
```

#### Request sample :

```
POST http://localhost:8080/api/users
Content-Type: application/json

{
  "firstName" : "TOTO",
  "lastName" : "BOBO",
  "birthday" : "1985-02-22T12:59:00Z",
  "address" : "1 avenue du General de Gaulle",
  "postalCode": "06600",
  "country" : "france",
  "email" : "aaaaa@gmail.fr",
  "phoneNumber" : "0659808596"
}
```

#### Response sample :

```
{
  "id": "66700c1bdd9d8c412653ba2a",
  "firstName" : "TOTO",
  "lastName" : "BOBO",
  "birthday" : "1985-02-22T12:59:00Z",
  "address" : "1 avenue du General de Gaulle",
  "postalCode": "06600",
  "country" : "france",
  "email" : "aaaaa@gmail.fr",
  "phoneNumber" : "0659808596"
}
```

### Show details a registered user

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
  "firstName" : "TOTO",
  "lastName" : "BOBO",
  "birthday" : "1985-02-22T12:59:00Z",
  "address" : "1 avenue du General de Gaulle",
  "postalCode": "06600",
  "country" : "france",
  "email" : "aaaaa@gmail.fr",
  "phoneNumber" : "0659808596"
}
```



## Project Structure

In the project root, configuration files for tools like git and maven files pom.xml, mvnw and mvnw.cmd.

- `/src/*` structure follows default Java structure.


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
