# Containers-p03

## Deployment

Run docker:

```sh
sudo service docker start
```

Execute minikube:

```sh
minikube start
```

Enable the ingress addon first to access the client webapp:

```sh
minikube addons enable ingress
```

Apply all the infrastructure resources and wait until everything is up:

```sh
kubectl apply -f manifest
```

## Associate domain name to IP to get access

```sh
echo "`minikube ip` cluster-ip" | sudo tee --append /etc/hosts >/dev/null
```

## Verification

The webapp will be accesible in [http://cluster-ip](http://cluster-ip)

The REST API for the service **toposervice** is also working in [http://cluster-ip/toposervice](http://cluster-ip/toposervice).

Any HTTP request will be handled properly. For example:

```sh
curl --location --request GET 'http://cluster-ip/toposervice/api/topographicdetails/sevilla'
```
