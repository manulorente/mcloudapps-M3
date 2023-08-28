# Containers-p04-rec

## Setup

Run docker:

```sh
sudo service docker start
```

Execute minikube with virtualbox driver:

```sh
minikube start
```

Enable the ingress addon first to access the client app:

```sh
minikube addons enable ingress
```

## Package and deploy. Need to commit changes first

```sh
 helm package ./EoloPlanner -d ./EoloPlanner/charts
 helm repo index ./EoloPlanner/charts
 helm repo add httpd-web-server https://raw.githubusercontent.com/manulorente/mcloudapps-M3/main/Containers-P04-rec/EoloPlanner/charts/
```

```sh
helm install eoloplanner httpd-web-server/EoloPlanner
```

To view al resources in the cluster in real time:

```sh
watch -n 1 kubectl get pods,services,deployments
```

or just:

```sh
minikube dashboard
```

## Associate domain name to IP to get access

```sh
echo "`minikube ip` cluster-ip" | sudo tee --append /etc/hosts >/dev/null
```

## Verification

The app will be accesible in [http://cluster-ip](http://cluster-ip)

The REST API for the service **toposervice** is also working in [http://cluster-ip/toposervice](http://cluster-ip/toposervice).

Any HTTP request will be handled properly. For example:

```sh
curl --location --request GET 'http://cluster-ip/toposervice/api/topographicdetails/sevilla'
```

Deleting mysql and mongodb pods to force them to be recreated and demostrat persistence on volumes:

```sh
kubectl delete pod <mysql_pod_name>
kubectl delete pod <mongodb_pod_name>
```

## Modifications to change user in containers to non-root

```sh
kubectl edit deployment <deployment_name>
```

## Build and push to DockerHub all services once modified  

```sh
./build.sh
```

or

```sh
./build.sh push
```
