## Student Management Application
This application manages student records using Spring Boot, Spring Data JPA, and RESTful APIs.

## Prerequisites
Before running this application, ensure you have the following installed:

-Java Development Kit (JDK) 8 or higher
-Apache Maven
-MySQL or any other relational database

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
spring.datasource.url=jdbc:mysql://localhost:3306/studentmanagement?serverTimeZone=UTC
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
java -jar target/student-management-spring-boot-1.0.jar

## Access the Application:
The application will be accessible at http://localhost:8080/api/students.

## API Endpoints
1.Add Student:
POST /api/students
Body: JSON representing Student object

2.Get All Students:
GET /api/students

3.Get Student by ID:
GET /api/students/{id}

4.Update Student by ID:
PUT /api/students/{id}
Body: JSON representing updated Student object

5.Delete Student by ID:
DELETE /api/students/{id}

## Error Handling
-Validation errors (e.g., empty name, invalid age) return Bad Request.
-When a student is not found by ID, it returns Bad Request.

## Exception Handling
Global exception handling is implemented using @ControllerAdvice to handle MethodArgumentNotValidException, StudentNotFoundException, and InvalidInputDataException.

## Dependencies
-Spring Boot Web
-Spring Data JPA
-MySQL Connector Java
-Jakarta Validation API (for validation annotations).


