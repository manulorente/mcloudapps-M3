#!/usr/bin/env bash

DOCKERHUB_NAME=manloralm
IMAGE_VERSION=v1.23

# Compile and publish Server
SERVER_IMAGE_NAME="${DOCKERHUB_NAME}/server:${IMAGE_VERSION}"

option=$1

if [ "$option" = "push" ]; then
  push=true
else
  push=false
fi

if [ $push = true ]; then
  printf "\n==> Compile and publish Server image named '%s' using Quarkus JIB\n" ${SERVER_IMAGE_NAME}
else
  printf "\n==> Compile Server image named using Quarkus JIB\n"
fi
mvn -f server/ install \
    -Dquarkus.container-image.group=${DOCKERHUB_NAME} \
    -Dquarkus.container-image.name=server \
    -Dquarkus.container-image.tag=${IMAGE_VERSION} \
    -Dquarkus.container-image.push=${push}

# Compile and publish WeatherService
WEATHERSERVICE_IMAGE_NAME="${DOCKERHUB_NAME}/weatherservice:${IMAGE_VERSION}"

if [ $push = true ]; then
  printf "\n==> Compile and publish WeatherService image named '%s' using Quarkus JIB\n" ${WEATHERSERVICE_IMAGE_NAME}
else
  printf "\n==> Compile WeatherService image named using Quarkus JIB\n"
fi
mvn -f weatherservice/ install \
    -Pnative \
    -Dquarkus.container-image.group=${DOCKERHUB_NAME} \
    -Dquarkus.container-image.name=weatherservice \
    -Dquarkus.container-image.tag=${IMAGE_VERSION} \
    -Dquarkus.container-image.push=${push}

# Compile and publish Planner
PLANNER_IMAGE_NAME="${DOCKERHUB_NAME}/planner:${IMAGE_VERSION}"

if [ $push = true ]; then
  printf "\n==> Compile and publish Planner image named '%s', using a multistage Docker file\n" ${PLANNER_IMAGE_NAME}
else
  printf "\n==> Compile Planner image named '%s', using a multistage Docker file\n" ${PLANNER_IMAGE_NAME}
fi
docker build -t ${PLANNER_IMAGE_NAME} ./planner
if [ $push = true ]; then
  docker push ${PLANNER_IMAGE_NAME}
fi

# Compile and publish TopoService
TOPSERVICE_IMAGE_NAME="${DOCKERHUB_NAME}/toposervice:${IMAGE_VERSION}"

if [ $push = true ]; then
  printf "\n==> Compile and publish TopoService image named '%s' using BuildPacks\n" ${TOPSERVICE_IMAGE_NAME}
else
  printf "\n==> Compile TopoService image named '%s' using BuildPacks\n" ${TOPSERVICE_IMAGE_NAME}
fi
mvn -f toposervice/ spring-boot:build-image \
    -Dspring-boot.build-image.imageName=${TOPSERVICE_IMAGE_NAME}
if [ $push = true ]; then
  docker push ${TOPSERVICE_IMAGE_NAME}
fi 

exit 0