# Flowchart
A RESTful API for creating and managing flowcharts.

# Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- MySQL

# Database Configuration

1. Create a new MySQL database for the application.
2. Update the application.properties file with your database credentials:



spring.datasource.url=jdbc:mysql://localhost:3306/flowchart_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


Replace your_username, your_password, and flowchart_db with your actual database credentials and database name.

# Installation
1. Clone the repository using Git:

bash

git clone https://github.com/PritiPrasad01/flowchart.git

2. Navigate into the project directory:
   bash
cd flowchart-api

1. Install the dependencies using Maven:

bash

mvn clean install

4. Build the project using Maven:
   bash
mvn package


# Running the Application
1. Run the application using Maven:

bash

mvn spring-boot:run

2. Alternatively, you can run the application using Java:
   bash
java -jar target/flowchart-api-0.0.1-SNAPSHOT.jar

1. The application will start on port 8080.

# Features

The following features have been implemented:

- Create Flowchart: Create a new flowchart with a unique ID, nodes, and edges.
- Fetch Flowchart: Fetch details of a flowchart by its ID, including its nodes and edges.
- Update Flowchart: Add or remove nodes and edges in an existing flowchart.
- Delete Flowchart: Delete an existing flowchart by its ID.
- Validate the graph: Validate the flowchart graph for consistency and correctness.
- Fetch outgoing edges: Fetch all outgoing edges for a given node.
- Query connected nodes: Query all nodes connected to a specific node (directly or indirectly).
- Swagger documentation: Implement basic Swagger documentation for the APIs.
- Unit tests: Implement unit tests for the API endpoints.

# Endpoints
The API endpoints are documented using Swagger. You can access the Swagger UI at:

http://localhost:8080/swagger-ui/index.html

# Testing

The application includes unit tests and integration tests. You can run the tests using Maven:


bash
mvn test
