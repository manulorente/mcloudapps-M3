#!/bin/bash

set -e

export DEPLOYMENT_NAME=EoloPlanner
export NAMESPACE=eoloplanner2
export REPO_NAME=httpd-web-server2
export URL=https://raw.githubusercontent.com/manulorente/mcloudapps-M3/main/Containers-P04-rec/EoloPlanner/charts/

function deploy() {

    # Package the Helm chart
    helm package ./$DEPLOYMENT_NAME -d ./$DEPLOYMENT_NAME/charts

    # Create or update the Helm repository index
    helm repo index ./$DEPLOYMENT_NAME/charts --url $URL

    # Add the Helm repository
    helm repo add $REPO_NAME $URL

    # Commit and push changes to Git
    git add .
    git commit -m "New version"
    git push

    # Update Helm repositories
    helm repo update

    # Install the Helm chart
    helm install $NAMESPACE $REPO_NAME/$DEPLOYMENT_NAME
}

function uninstall() {

    # Uninstall the Helm chart and remove the Helm repository
    helm uninstall $NAMESPACE
    helm repo remove $REPO_NAME
    rm -rf ./$DEPLOYMENT_NAME/charts
    
}

if [ $# -eq 0 ]; then
    echo "No option provided. Please use -i to deploy and install, or -u to uninstall."
    exit 1
fi

while getopts ":iu" opt; do
    case $opt in
        i) # Deploy and Install Helm chart
            deploy
            ;;
        u) # Uninstall Helm chart
            uninstall
            ;;
        \?) # Invalid option
            echo "Invalid option: -$OPTARG" >&2
            exit 1
            ;;
    esac
done
