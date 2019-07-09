# Store Manager

This project implements a typical e-commerce backend for managing the database, providing Java methods to store and retrieve data.

## Getting Started

[Persistence.xml](src/main/resources/META-INF/persistence.xml) contains the database parameters. The user and password set in this file are not secure and they should be replaced in a production server. The file also contains the *Table Generation Strategy* set to *create* by default, which is no longer needed once the tables are initiated in the database and it needs to be deleted to avoid warnings while executing the code.

The following diagram is created:

![Diagram](images/diagram.png "Diagram")

### Project structure
The Java classes are classified as follows:
* **Aspect**: AOP framework components.
* **DTO**: Encapsulated data sent to the final users.
* **Exception**: Custom exceptions.
* **Model**: Representations from database records.
* **Operations**: Basic functions for managing the database.
* **Service**: Operations for final users, either public or private (they need some kind of authentication).

## Running

This repository has several dependencies (see [pom.xml](pom.xml)). There's no need to get Maven to use these libraries as long as the required .jar files are downloaded for the project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details