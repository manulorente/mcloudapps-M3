# Load testing and fault tolerance with Kubernetes

[Video]()

## Setup

### Start minikube

``` bash
minikube start
```

## Load testing with JMeter

### Deploy the sample application

``` bash
kubectl apply -f webapp-stateless/k8s/webapp.yaml
```

### Get the service URL to run the load test

``` bash
minikube service webapp --url
```

#### Scenario 1: 100% success rate - no chaos

``` bash
jmeter -n -t jmeter/success.jmx -l jmeter/success.jtl
```

### Scale the application

``` bash
kubectl scale deployment webapp --replicas=2
```

### Run jmeter

``` bash
jmter -n -t jmeter/chaos.jmx -l chaos.jtl
```

## Native development in Kubernetes with Okteto

### Deploying to Kubernetes  

``` bash
kubectl apply -f infraestructure
minikube service server-service
```
