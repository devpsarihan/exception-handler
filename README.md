# Exception Handler App
### Project Goal
This app was developed to explain handling exceptions and resource bundle messages

### Tech Stack
* Java 21
* Spring Boot v3.4.3
* PostgreSQL
* Flyway
* Testcontainers
* Docker

### Run the Project
* Compile with Java 21
* Go to the project folder and run this commands
```
  $ cd exception-handler
  $ mvn clean install 
  $ docker build -t exception-handler-image .
  $ docker-compose -f docker-compose.yaml up -d
```
* If you want to add host file
```
0.0.0.0 exception-handler
```

### Curl Commands
```
curl --location 'http://localhost:8080/v1/products' \
--header 'Content-Type: application/json' \
--data '{
    "name": "<string>",
    "categoryId": <integer>,
    "description": "<string>",
    "price": <number>,
    "quantity": <integer>
}'
```
```
curl --location 'http://localhost:8080/v1/products/{{productId}}' \
--header 'Accept: */*'
```
```
curl --location --request PUT 'http://localhost:8080/v1/products/4' \
--header 'Content-Type: application/json' \
--data '{
    "name": "<string>",
    "categoryId": <integer>,
    "description": "<string>",
    "price": <number>,
    "quantity": <integer>,
    "status": <integer>
}'
```
