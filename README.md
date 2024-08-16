## Employee Management Application
This application manages employees records using Spring Boot, Spring Data JPA, and RESTful APIs.

##User credentials
with user creadintial user register then login to access the api end point

## Prerequisites
Before running this application, ensure you have the following installed:

-Java Development Kit (JDK) 8 or higher
-Apache Maven
-MySQL or any other relational database
-spring security(Jwt token) for authentication and authorazation
-Eruka server
-Eureka client

## Setup Instructions
-Clone the repository:
git clone <repository_url>
cd <repository_name>

## Database Configuration:
Create a MySQL database named studentmanagement (or configure database settings in application.properties as per your setup).

## Application Configuration:
-Open src/main/resources/application.properties.
-Configure database connection properties:

### Database configuration
spring.datasource.name=test
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=root
spring.datasource.password=root

### Hibernate properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

## Build and Run:
Build the application using Maven:
mvn clean package

## Run the application:
java -jar target/employees-management-spring-boot-1.0.jar

## Access the Application:
The application will be accessible at http://localhost:8080/employees.
is the getWay url to accessing different microservices in one url

## API Endpoints
1.Add Employee:
POST /employees
Body: JSON representing Student object

2.Get All Employee:
GET /employees

3.Get Employee by ID:
GET /employees/{id}

4.Update Employee by ID:
PUT /employees/{id}
Body: JSON representing updated Student object

5.Delete Employee by ID:
DELETE /employees/{id}

## Error Handling
-Validation errors (e.g., empty name, invalid age) return Bad Request.
-When a student is not found by ID, it returns Bad Request.

## Dependencies
-Spring Boot Web
-Spring Data JPA
-MySQL Connector Java
-Jakarta Validation API (for validation annotations).
-Erueka server
-Eureka client
-security
-jwt api,impl,lib


