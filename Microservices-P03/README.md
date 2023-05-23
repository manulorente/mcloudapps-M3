# Microservices-p03

## Getting Started

### Start docker service

  ```bash
  sudo service docker start
  ```

### Launch Minikube
  
  ```bash
  minikube start 
  ```
  
### Enable ingress

  ```bash
  minikube addons enable ingress
  ```

### Associate domain name to IP to get access

  ```sh
  echo "`minikube ip` booksapp" | sudo tee --append /etc/hosts >/dev/null
  ```

### Run mysql service

  ```bash
  kubectl apply -f k8s/db
  ```

### Run monolith application

  ```bash
  kubectl apply -f k8s/monolith-only
  ```

### Run monolotih and users microservices application

  ```bash
  kubectl apply -f k8s/monolith-microservice
  ```

## Deploying the application

### Build and publish the monolith with docker
  
  ```bash
  docker build -t monolith:1.0 .
  ```

  ```bash
  docker tag monolith:1.0 manloralm/monolith:1.0
  docker push manloralm/monolith:1.0
  ```

### Build and publish the users microservice with docker
  
  ```bash
  docker build -t microservice:1.0 .
  ```

  ```bash
  docker tag microservice:1.0 manloralm/microservice:1.0
  docker push manloralm/microservice:1.0
  ```

## Test the API with Postman collection
