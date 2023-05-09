# Ejecución

## Arranque de mysql y kafka

docker-compose -f docker-compose-mysql.yaml up

## Parada de mysql y Kafka

docker-compose -f docker-compose-mysql.yaml down

## Terminal en gateway-service

mvn spring-boot:run

## Terminal en order-service

mvn spring-boot:run

## Terminal en inventory-service

mvn spring-boot:run

## Terminal en customer-service

mvn spring-boot:run

## Terminal en delivery-service

mvn spring-boot:run

# API endpoints

Se adjunta 4 colecciones en la carpeta postman. Varios escenarios posibles:  

## orderState    rejectionReason    comentario

  APPROVED                                Scenario 1 - El pedido cumple con todos los requisitos del sistema  
  REJECTED        INSUFFICIENT_CREDIT     Scenario 2 - El pedido se ha rechazado porque falta saldo en el cliente  
  REJECTED        SOLD_OUT                Scenario 3 - El pedido se ha rechazado porque falta stock del producto  
  REJECTED        SOLD_OUT                Scenario 4 - El pedido se ha rechazado porque no se puede repartir en esa ciudad  

# Notas  

Hay que ir recuperando el ID de los pedidos y clientes que se van creando para poder hacer las pruebas de los endpoints de los servicios.
