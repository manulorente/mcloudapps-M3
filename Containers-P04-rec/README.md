# Containers-p04-rec

## Links

[![Artifact Hub](https://img.shields.io/endpoint?url=https://artifacthub.io/badge/repository/eoloplanner-mlorente-jagarrido)](https://artifacthub.io/packages/search?repo=eoloplanner-mlorente-jagarrido)

[GitHub repository](https://github.com/manulorente/mcloudapps-M3/tree/main/Containers-P04-rec)

[Demo](https://drive.google.com/file/d/1S99l9wLMxT_w4QqOew7zXWLjIbi7Re9H/view)

## Setup

Run docker:

```bash
sudo service docker start
```

Run minikube with enough resources and cilium network. Need to disable cni to avoid conflicts with cilium and have cilium installed.

```bash
minikube start --memory 10240 --cpus 4 --network-plugin=cni --cni=cilium

```

Enable dashboard and metrics:

```bash
minikube addons enable dashboard
minikube addons enable metrics-server
```

Install cilium if not installed:

```bash
curl -L --fail --remote-name-all https://github.com/cilium/cilium-cli/releases/download/v0.15.8/cilium-linux-amd64.tar.gz{,.sha256sum}
sha256sum --check cilium-linux-amd64.tar.gz.sha256sum
sudo tar xzvfC cilium-linux-amd64.tar.gz /usr/local/bin
rm cilium-linux-amd64.tar.gz{,.sha256sum}
```

```bash
cilium install --version 1.14.2 --set kubeProxyReplacement=true --set ingressController.enabled=true --set ingressController.loadbalancerMode=dedicated
```

Verify cilium is running:

```bash
cilium status
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
