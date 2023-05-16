# Microservices-p02

## Start docker service

  ```bash
  sudo service docker start
  ```

## Launch Axon Server
  
  ```bash
  docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver:4.5.12
  ```
  
## Launch the app in shopping-cart folder

  ```bash
  mvn spring-boot:run
  ```

## To stop and remove the containers

  ```bash
  sudo docker stop $(docker ps -aq)
  sudo docker rm $(docker ps -aq)
  ```
