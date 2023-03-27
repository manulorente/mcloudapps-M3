# Containers-p02

## Running all services

``` bash
kubectl apply -f manifest
```

Server service takes ~110s and needs to restart at least 3 time while the database services are being deployed  

``` bash
minikube service server-service
```
