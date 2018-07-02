# Storeroom API REST (storeroom-rest-api)

<!-- Badger start badges -->
[![Build Status](https://travis-ci.org/ismaelcabanas/storeroom-rest-api.svg)](https://travis-ci.org/ismaelcabanas/storeroom-rest-api)
<!-- Badger end badges -->

This application shows a API REST to manage a particular storeroom.

* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Getting it](#getting-it)
  * [Installing it](#installing-it)
  * [Running it](#running-it)
* [Running the tests](#running-the-tests)
  * [Unit tests](#unit-tests)
  * [Integration tests](#integration-tests)
* [Deployment & tools](#deployment--tools)
  * [Development](#development)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You need the following tools installed and configured in order to run this project locally:
* Git
* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) / [jEnv](http://www.jenv.be/)
* Gradle

### Getting it

*  Simply 'git clone' the repository on your local machine through your favourite git client.

### Installing it

* Locate the cloned repository folder and use gradle command 'build' to compile and build the project (A 'gradle wrapper' is provided with the project).
```
cd storeroom-rest-api/
```
```
./gradlew build
```

### Running it

* Run the project using gradle command 'bootRun' and wait for it to be ready:
```
./gradlew bootRun
```
* Check the 'health' status endpoint:
```
http://localhost:8100/health
```

## Running the tests

In order to run unit and integrations tests locally you need execute the following 'gradle' commands:

### Unit Tests

Unit tests are executed every time you build the project and can be executed manually using:

```
./gradlew test
```

### Integration Tests

They are executed by Travis on every Pull Request and can be executed manually using:
```
./gradlew integrationTest
```

## Deployment and Tools

### Development
* [Travis](https://travis-ci.org/ismaelcabanas/storeroom-rest-api/)


