#!/bin/bash

SERVICE=microservice # monolith or microservice
DOCKER_USER=manloralm
TAG_VERSION=latest

# Build and push the image
cd ${SERVICE}
docker build -t ${SERVICE}:${TAG_VERSION} .
docker tag ${SERVICE}:${TAG_VERSION} ${DOCKER_USER}/${SERVICE}:${TAG_VERSION}
docker push ${DOCKER_USER}/${SERVICE}:${TAG_VERSION}
cd ..

# Create all the kubernetes resources
kubectl apply -f k8s/db

# Wait for mysql to be ready
sleep 10

# Create the rest of the resources monolith or microservice
if [ ${SERVICE} == "monolith" ]; then
    kubectl apply -f k8s/monolith-only
else
    kubectl apply -f k8s/monolith-microservice
fi