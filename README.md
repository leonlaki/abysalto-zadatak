# Tehnički zadatak - Junior Developer
Spring Boot REST API for ordering different priced items

## Technologies used
- Java 21
- Spring Boot 4.0.2
- Spring Data JPA (Hibernate)
- H2 In-Memory Database
- Bean Validation
- Gradle 

## Features
- Create an order (POST method)
- Get all orders (GET method)
- Change the status of an order (PATCH method)
- Sort all orders based on total price (ascending/descending) (GET method)
- Convert to desired currency, default is EUR (GET method)

## How to run application
### Requirements
- Java installed
- Gradle set up

### Steps
1. Clone the repository or download zipped repository
2. Run the application using Gradle (in Windows):
```bash
./gradlew bootRun
```
3. Application runs on http://localhost:8080

## H2-console
- H2 console is available at: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:abysalto-db
- Username: sa
- Password: