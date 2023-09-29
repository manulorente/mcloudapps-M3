# Containers-p04-rec

## Links

[![Artifact Hub](https://img.shields.io/endpoint?url=https://artifacthub.io/badge/repository/eoloplanner-mlorente-jagarrido)](https://artifacthub.io/packages/search?repo=eoloplanner-mlorente-jagarrido)

[GitHub repository](https://github.com/manulorente/mcloudapps-M3/tree/main/Containers-P04-rec)

## Setup

Run docker:

```bash
sudo service docker start
```

Run minikube with enough resources:

```bash
minikube start --memory=12288 --cpus=4
```

Enable ingress and metrics-server:

```bash
minikube addons enable ingress

minikube addons enable metrics-server
```

## Running the app

To package and publish and install the helm chart:

```bash
./deploy.sh -i
```

To uninstall:

```bash
./deploy.sh -u
```

## Associate domain name to minikube

```bash
echo "`minikube ip` mastercloudapps" | sudo tee --append /etc/hosts >/dev/null
```

## Verification

The app will be accesible in [http://mastercloudapps](http://mastercloudapps)

The REST API for the service **toposervice** is also working in [http://mastercloudapps/toposervice](http://mastercloudapps/toposervice).

Any HTTP request will be handled properly. For example:

```bash
curl --location --request GET 'http://mastercloudapps/toposervice/api/topographicdetails/sevilla'
```
