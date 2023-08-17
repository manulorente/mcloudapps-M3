# Docker and containers

Dockerize few microservices to operate together.  

## Dev environment

### Create docker-compose-dev.yml to configure MySQL, MongoDB and RabbitMQ  

``` bash
docker-compose -f docker-compose-dev.yml up
```  

or

``` bash
docker run -d --name mysql --network host -p 3306:3306 -e MYSQL_HOST=mysql -e MYSQL_DATABASE=eoloplantsDB -e MYSQL_ROOT_PASSWORD=password --restart=on-failure mysql:8.0
```

``` bash
docker run -d --name mongo --network host -p 27017:27017 -e MONGO_PORT=27017 -e MONGO_HOST=mongodb -e MONGO_INITDB_DATABASE=topo -e  MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=password --restart=on-failure mongo:5.0-focal
```

``` bash
docker run -d --name rabbitmq --network host -p 5672:5672 -p 15672:15672 -e RABBIT_PORT=5672 -e RABBITMQ_HOST=rabbitmq -e RABBITMQ_DEFAULT_USER=root -e RABBIT_DEFAULT_PASS=password --restart=on-failure rabbitmq:3.11-management
```

### Build and run services in containers  

``` bash
docker build -t manloralm/weatherservice:v0.1 ./weatherservice  
docker run -it -d --name weatherservice --network host -p 9090:9090 -e QUARKUS_GRPC_SERVER_HOST=weatherservice -e QUARKUS_GRPC_SERVER_PORT=9090 --restart=on-failure manloralm/weatherservice:v0.1
```

``` bash
mvn -f ./toposervice/pom.xml -Pnative spring-boot:build-image -Dspring-boot.build-image.imageName=manloralm/toposervice:v0.1  
docker run -it -d --name toposervice --network host -p 8181:8181 -e TOPO_PORT=8181 -e SPRING_DATA_MONGODB_HOST=mongodb -e SPRING_DATA_MONGODB_PORT=27017 -e SPRING_DATA_MONGODB_DATABASE=topoDB -e SPRING_DATA_MONGODB_AUTHENTICATION-DATABASE=admin -e SPRING_DATA_MONGODB_USERNAME=root -e SPRING_DATA_MONGODB_PASSWORD=password --restart=on-failure manloralm/toposervice:v0.1
```

``` bash
mvn -f ./planner/pom.xml compile jib:build -DskipTests -Dimage=manloralm/planner:v0.1  
docker run -it -d --name planner --network host -p 8080:8080 -e TOPO_HOST=toposervice -e TOPO_PORT=8181 -e GRPC_CLIENT_WEATHERSERVER_ADDRESS=static://weatherservice:9090 -e SPRING_CLOUD_STREAM_RABBIT_BINDER_NODES=rabbitmq:5672 -e SPRING_RABBITMQ_HOST=rabbitmq -e SPRING_RABBITMQ_PORT=5672 -e SPRING_RABBITMQ_USERNAME=root -e SPRING_RABBITMQ_PASSWORD=password --restart=on-failure manloralm/planner:v0.1
```

``` bash
mvn -f ./server/pom.xml clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.image=manloralm/server:v0.1  
docker run -it -d --name server --network host -p 3000:3000 -e QUARKUS_DATASOURCE_DB_KIND=mysql -e QUARKUS_DATASOURCE_JDBC_URL=jdbc:mysql://mysql/eoloplantsDB -e QUARKUS_DATASOURCE_USERNAME=root -e QUARKUS_DATASOURCE_PASSWORD=password -e RABBITMQ_HOST=rabbitmq -e RABBITMQ_PORT=5672 -e RABBITMQ_USERNAME=root -e RABBITMQ_PASSWORD=password --restart=on-failure manloralm/server:v0.1
```

## Prod environment  

### Build and publish all services  

``` bash
./build-push-services.sh 
```

### Deploy all services in containers

``` bash
docker-compose -f docker-compose-prod.yml up
```

## Services endpoint  

The app will be served in <http://localhost:8080>  

## To remove all containers and database volumes

``` bash
docker rm -f $(docker ps -a -q)
sudo rm -rf data || true
```
