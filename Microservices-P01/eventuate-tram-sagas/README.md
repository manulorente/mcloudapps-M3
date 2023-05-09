# Microservices-P01

Need to configure Java 8 first in order to run the project.

## Deployment

1. Start docker service

``` sh
sudo service docker start
```

2. Go to app folder

``` sh
cd eventuate-tram-sagas
```

3. Run

``` sh
./gradlew assemble
```

4. Configure Docker Host IP getting the IP address of the docker0 interface

``` sh
ip addr show docker0 | grep -Po 'inet \K[\d.]+'
export DOCKER_HOST_IP=<DOCKER_HOST_IP_ADDRESS>
```

5. Run

``` sh
./gradlew mysqlComposeBuild
```

and then run:

``` sh
./gradlew mysqlComposeUp
```

### Tests

For this assignments, 3 new test cases have been added to:
`end-to-end-test/src/test/java/io.eventuate.examples.tram.sagas.ordersandcustomers.endtoendtests`

Feel free to execute them once the previous `gradlew` command have been succesfully completed, by running:

``` sh
./gradlew endToEndTests
```

The new tests added for this project can be found at:
`eventuateTramApp/end-to-end-tests/src/test/java/io/eventuate/examples/tram/sagas/ordersandcustomers/endtoendtests/AssignmentTests.java`

### To stop and remove the containers

``` sh
./gradlew mysqlComposeDown
sudo docker stop $(docker ps -aq)
sudo docker rm $(docker ps -aq)
```

### Usage  

- **Orders Service**  - <http://localhost:8081/swagger-ui.html>
- **Customers Service** - <http://localhost:8082/swagger-ui.html>
- **Products Service** - <http://localhost:8083/swagger-ui.html>
- **API Gateway** - <http://localhost:8084/swagger-ui.html>
