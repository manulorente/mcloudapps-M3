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
 helm package ./EoloPlanner -d ./EoloPlanner/charts && helm repo index ./EoloPlanner/charts && helm repo add httpd-web-server https://raw.githubusercontent.com/manulorente/mcloudapps-M3/main/Containers-P04-rec/EoloPlanner/charts/ && helm repo update
```

```sh
git add . && git commit -m "New version" && git push
```

To install:

```sh
helm install eoloplanner httpd-web-server/EoloPlanner
```

To uninstall:

```sh
helm uninstall eoloplanner && helm repo remove httpd-web-server && rm -rf ./EoloPlanner/charts
```

## Verification

The app will be accesible in [http://cluster-ip](http://cluster-ip)

The REST API for the service **toposervice** is also working in [http://cluster-ip/toposervice](http://cluster-ip/toposervice).

Any HTTP request will be handled properly. For example:

```sh
curl --location --request GET 'http://cluster-ip/toposervice/api/topographicdetails/sevilla'
```
