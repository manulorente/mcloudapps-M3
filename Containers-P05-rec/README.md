# Load testing and fault tolerance with Kubernetes

[Video]()

## Setup

### Start minikube

``` bash
minikube start
```

## Load testing with JMeter

### Delete all custer resources

``` bash
kubectl delete all --all
```

#### Scenario 1: 100% success rate - no chaos

Deploy database and application with single pod

``` bash
kubectl apply -f webapp-stateless/k8s/db.yaml
kubectl apply -f webapp-stateless/k8s/webapp-single-pod.yaml
```

Get the service URL to run the load test

``` bash
minikube service webapp --url
```

``` bash
jmeter -n -t jmeter/success.jmx -l jmeter/success.jtl
```

#### Scenario 2: Single pod with errors because of chaos

Apply chaos to remove one pod at a time

``` bash
./webapp-stateless/chaos-pod-monkey.sh
```

#### Scenario 3: Two pods to reduce the error rate

Apply chaos to remove one pod at a time

``` bash
./webapp-stateless/chaos-pod-monkey.sh
```

Apply redundancy to the deployment

``` bash
kubectl delete -f webapp-stateless/k8s/webapp-single-pod.yaml
kubectl apply -f webapp-stateless/k8s/webapp-redundant-pods.yaml
```

#### Scenario 4: Apply Istio to the deployment to reduce errors

``` bash
kubectl apply -f webapp-stateless/k8s/istio.yaml
```

## Native development in Kubernetes with Okteto

### Deploying to Kubernetes  

``` bash
kubectl apply -f infraestructure
minikube service server-service
```

### Developing server-service in Kubernetes with Okteto

``` bash
okteto up
```
