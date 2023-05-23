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

## Deploying the application

Run build script to build the docker images and push them to docker hub

  ```bash
  ./build_and_push.sh
  ```

## Test the API with Postman collection
