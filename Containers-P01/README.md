# Docker and containers

Dockerize few microservices to operate together.  

## Create docker-compose-dev.yml to configure MySQL, MongoDB and RabbitMQ  

``` bash
 docker-compose -f docker-compose-dev.yml up
```  

## Dockerize and publish all services in DockerHub  

``` bash
./build-push-services.sh 
```

## Execute services running below command

``` bash
 docker-compose -f docker-compose-prod.yml up
```

## Services endpoint  

The app will be served in <http://localhost:3000>  
