#!/usr/bin/env bash

DOCKERHUB_NAME=manloralm
IMAGE_TAG=v0.1

# Build and push all services to DockerHub
build_and_push() {

    service=$1

    if [ -z "$service" ]; then
        printf "No service specified\n"
        exit 1
    fi

    # Compile and publish Server using Quarkus plugin for Docker
    if [ "$service" = "server" ]; then
        printf "Building ${service%%/} using Quarkus plugin for Docker\n"
        mvn -f ./$service/pom.xml clean package -Dquarkus.container-image.build=true \ 
            -Dquarkus.container-image.image.name=${DOCKERHUB_NAME}/${service}:${IMAGE_TAG}
        docker tag $DOCKERHUB_NAME/$service:$IMAGE_TAG $DOCKERHUB_NAME/$service:$IMAGE_TAG
        docker push $DOCKERHUB_NAME/$service:$IMAGE_TAG
    fi

    # Compile and publish WeatherService using multi-stage Dockerfile
    if [ "$service" = "weatherservice" ]; then
        printf "Building ${service%%/} using multi-stage Dockerfile\n"
        docker build -t $DOCKERHUB_NAME/$service:$IMAGE_TAG ./$service
        docker push $DOCKERHUB_NAME/$service:$IMAGE_TAG    
    fi

    # Compile and publish Planner using JIB plugin for Spring
    if [ "$service" = "planner" ]; then
        printf "Building ${service%%/} using JIB plugin for Spring\n"
        mvn -f ./$service/pom.xml compile jib:build -DskipTests -Dimage=$DOCKERHUB_NAME/$service:$IMAGE_TAG
    fi

    # Compile and publish TopoService using Buildpacks with GraalVM Native
    if [ "$service" = "toposervice" ]; then
        printf "Building ${service%%/} using Buildpacks with GraalVM Native\n"
        mvn -f ./$service/pom.xml -Pnative spring-boot:build-image \
            -Dspring-boot.build-image.imageName=${DOCKERHUB_NAME}/${service}:${IMAGE_TAG}
        docker push ${DOCKERHUB_NAME}/${service}:${IMAGE_TAG}
    fi
}

for service in */; do
    build_and_push ${service%%/}
done

printf "All services built, pushed to DockerHub and deployed\n"