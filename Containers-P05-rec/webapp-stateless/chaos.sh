#!/bin/bash

+ sleep 5
+ true
+ kubectl --namespace default -o 'jsonpath={.items[*].metadata.name}' get pods
+ tr ' ' '\n'
+ grep -v db
+ shuf
+ head -n 1
+ xargs -t --no-run-if-empty kubectl --namespace default delete pod


