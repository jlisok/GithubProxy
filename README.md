# GitHubProxy
This project is a Java 17 Spring Boot application that serves as Proxy for github API. 

## Getting Started
To get started with the project, clone the repository to your local machine. Note that the code exposed is only in the initial development phase. Many things will appear as the application progresses further.

On the ASAP TODO list:
- security
- add more integration tests 
- adding more unit tests
- docker

## Technical Stack Used

The algorithms have been implemented using the following:

- Java 17
- Spring
- Junit 5
- Mockito
- maven


## Prerequisites
To build and run the project, you will need to have Java 17 installed on your machine. 
You can download it from the official website of your choice, or use your preferred package manager to install it.
You will also need to have Apache Maven installed, which can be downloaded from the official Apache Maven website. You can download the source code by cloning this repository:

```bash
git clone https://github.com/jlisok/GithubProxy.git
```

## Building and Running
To build and (if applicable) install the project, navigate to the root directory and run the following command:

```bash
mvn clean install
```

This will compile the project and create an executable JAR file in the target/ directory.

For now, it is advices to run the project using IDE of your choice for test purposes. No contenerization support added so far.

After starting the server, application is available at `http://localhost:8080`.

## Configuration
The project can be configured by editing the application.yaml file located in the app/src/main/resources/application.yaml directory.

## API
This application does not support GUI as of date. It exposes 1 API under the following url: `/users/{userName}/account-activity`. Aforementioned service serves a list of user repositories. It supports pagination, which is configurable via optionable RequestParam `pageNumber`. 

## Swagger
Swagger ui is available on `http://localhost:8080/swagger-ui/index.html#`.


## Contributing
Contributions to the project are welcome! If you find a bug or have a feature request, please open an issue on the repository.
