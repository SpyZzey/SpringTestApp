# Information
This is a test project to demonstrate the use of the following technologies:
- Spring Boot
- Spring Data JPA
- Spring Security

It only offers a REST API to manage a list of weather forecasts.
The API is secured with InMemory authentication, which is hardcoded and should not be used in production.

# Prerequisites
- Java 20
- Maven 3.6.3
- MySQL 8.0.23
- Postman (optional)

# Installation
Please clone the repository and rename the file `example.application.properties` to `application.properties` and
adjust the values to your needs (e.g. database connection).

You can start the application with the following command:
`mvn spring-boot:run` if you have Maven installed or `./mvnw spring-boot:run` if you don't have Maven installed.

# API - Postman
You can use the following link to import the Postman collection:\
https://www.postman.com/lunar-satellite-968925/workspace/testing/collection/21064119-04a61c66-6295-44f0-8067-abd11db2e33a?action=share&creator=21064119

by Simon Brebeck