#!/usr/bin/env bash

DOCKERHUB_NAME=mastercloudapps
IMAGE_VERSION=v1.23

# Compile and publish Server
SERVER_IMAGE_NAME="${DOCKERHUB_NAME}/server:${IMAGE_VERSION}"

printf "\n==> Compile and publish Server image named '%s' using Quarkus JIB\n" ${SERVER_IMAGE_NAME}
mvn -f server/ install \
    -Dquarkus.container-image.push=true \
    -Dquarkus.container-image.group=${DOCKERHUB_NAME} \
    -Dquarkus.container-image.name=server \
    -Dquarkus.container-image.tag=${IMAGE_VERSION} \
    -Dquarkus.container-image.push=true

# Compile and publish WeatherService
WEATHERSERVICE_IMAGE_NAME="${DOCKERHUB_NAME}/weatherservice:${IMAGE_VERSION}"

printf "\n==> Compile and publish WeatherService image named '%s' using Quarkus Docker Native\n" ${WEATHERSERVICE_IMAGE_NAME}
mvn -f weatherservice/ install \
    -Pnative \
    -Dquarkus.container-image.push=true \
    -Dquarkus.container-image.group=${DOCKERHUB_NAME} \
    -Dquarkus.container-image.name=weatherservice \
    -Dquarkus.container-image.tag=${IMAGE_VERSION} \
    -Dquarkus.container-image.push=true

# Compile and publish Planner
PLANNER_IMAGE_NAME="${DOCKERHUB_NAME}/planner:${IMAGE_VERSION}"

printf "\n==> Compile and publish Planner image named '%s', using a multistage Docker file\n" ${PLANNER_IMAGE_NAME}
docker build -t ${PLANNER_IMAGE_NAME} ./planner
docker push ${PLANNER_IMAGE_NAME}

# Compile and publish TopoService
TOPSERVICE_IMAGE_NAME="${DOCKERHUB_NAME}/toposervice:${IMAGE_VERSION}"

printf "\n==> Compile and publish TopoService image named '%s' using BuildPacks\n" ${TOPSERVICE_IMAGE_NAME}
mvn -f toposervice/ spring-boot:build-image \
    -Dspring-boot.build-image.imageName=${TOPSERVICE_IMAGE_NAME}

docker push ${TOPSERVICE_IMAGE_NAME}

exit 0