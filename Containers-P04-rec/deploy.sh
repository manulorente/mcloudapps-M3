helm package ./EoloPlanner -d ./EoloPlanner/charts && helm repo index ./EoloPlanner/charts --url https://raw.githubusercontent.com/manulorente/mcloudapps-M3/main/Containers-P04-rec/EoloPlanner/charts/

helm repo add httpd-web-server https://raw.githubusercontent.com/manulorente/mcloudapps-M3/main/Containers-P04-rec/EoloPlanner/charts/

git add . && git commit -m "New version" && git push

helm repo update

helm install eoloplanner httpd-web-server/EoloPlanner