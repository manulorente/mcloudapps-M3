# Docker and containers

Dockerize few microservices to operate together.  

## Dev environment: launch services manually  

### Create docker-compose-dev.yml to configure MySQL, MongoDB and RabbitMQ  

``` bash
 docker-compose -f docker-compose-dev.yml up
```  

``` bash
 docker run -it -e DOCKER_USER=manloralm -e WEATHER_PORT=9090 -e WEATHER_HOST=weatherservice -d -p 9090:9090 manloralm/weatherservice:v0.1
```

``` bash
 docker run -it -e TOPO_PORT=8181 -e TOPO_HOST=toposervice -e MONGO_PORT=27017 -e MONGO_HOST=localhost -e MONGO_INITDB_DATABASE=topo -d -p 8181:8181 manloralm/toposervice:v0.1
```

``` bash
 docker run -it -e WEATHER_PORT=9090 -e RABBIT_PORT=5672 -d -p 8181:8181 manloralm/planner:latest
```

``` bash
 docker run -it -e DOCKER_USER=manloralm -e MYSQL_HOST=mysql -e MYSQL_USER=root -e MYSQL_ROOT_PASSWORD=password -e MYSQL_PASSWORD=password -e MYSQL_DATABASE=eoloplantsDB -e RABBIT_PORT=5672 -d -p 3000:3000 manloralm/server:v0.1
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
