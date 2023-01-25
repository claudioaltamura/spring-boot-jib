# spring-boot-jib

Demo project for Spring Boot with Jib

* added Spring DevTools
* local registry

## run local registry

    docker run -d -p 5000:5000 --name registry registry:latest

check registry

    dpcker ps

## Build

    mvn compile jib:build

or

    mvn package

check image

    docker pull localhost:5000/spring-boot-jib:latest

## Run app
    curl http://localhost:8080/