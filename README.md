# spring-boot-jib

Demo project for Spring Boot with Jib

* added Spring DevTools
* using minikube private registry

## Minikube

#### Private Registry with minikube

    minikube addons enable registry

https://minikube.sigs.k8s.io/docs/handbook/registry/#using-a-private-registry

#### Start
    
    minikube start 

## Build

    eval $(minikube docker-env)

https://minikube.sigs.k8s.io/docs/handbook/pushing/#1-pushing-directly-to-the-in-cluster-docker-daemon-docker-env

    mvn compile jib:build

or

    mvn package

check image
    
    export REGISTRY_PORT=61363
    
    curl http://localhost:$REGISTRY_PORT/v2/_catalog | jq .

    curl http://localhost:$REGISTRY_PORT/v2/spring-boot-jib/tags/list | jq .

https://docs.docker.com/registry/spec/api/

## Run app

    minikube kubectl -- create deployment spring-boot-jib --image=localhost:$REGISTRY_PORT/spring-boot-jib:latest
    minikube kubectl -- get deployments

    minikube kubectl -- expose deployment spring-boot-jib --type=NodePort --port=8080
    minikube kubectl -- get svc

    export NODE_PORT=$(minikube kubectl -- get services/spring-boot-jib -o go-template='{{(index .spec.ports 0).nodePort}}')
    echo NODE_PORT=$NODE_PORT

    curl http://$(minikube ip):$NODE_PORT/superheroes | jq . 

## Cleanup
    
    minikube kubectl -- delete svc spring-boot-jib
    minikube kubectl -- get svc
    
    minikube kubectl -- delete deployment spring-boot-jib
    minikube kubectl -- get deployments