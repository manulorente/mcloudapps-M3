#!/bin/sh 
SERVICE_NAME=events_service 
PATH_TO_JAR=/home/ubuntu/m.lorente-j.garrido-0.0.1-SNAPSHOT.jar 
PID_PATH_NAME=/tmp/events_service-pid 

export BUCKET_NAME=mca-events
export REGION=us-east-1
export SERVER_PORT=443
export RDS_ENDPOINT=eventsdb.ckbzargdvke9.us-east-1.rds.amazonaws.com
export RDS_DATABASE=events
export RDS_USER=root
export RDS_PASS=12345678

case $1 in 
     start)
          echo "Starting $SERVICE_NAME ..."
     if [ ! -f $PID_PATH_NAME ]; then 
          nohup java -jar -Dspring.profiles.active=production $PATH_TO_JAR --server.port=$SERVER_PORT /tmp 2>> /dev/null >>/dev/null &      
          echo $! > $PID_PATH_NAME  
          echo "$SERVICE_NAME started ..."         
     else 
          echo "$SERVICE_NAME is already running ..."
     fi
     ;;
     stop)
     if [ -f $PID_PATH_NAME ]; then
          PID=$(cat $PID_PATH_NAME);
          echo "$SERVICE_NAME stoping ..." 
          kill $PID;         
          echo "$SERVICE_NAME stopped ..." 
          rm $PID_PATH_NAME       
     else          
          echo "$SERVICE_NAME is not running ..."   
     fi    
     ;;    
     restart)  
     if [ -f $PID_PATH_NAME ]; then 
          PID=$(cat $PID_PATH_NAME);    
          echo "$SERVICE_NAME stopping ..."; 
          kill $PID;           
          echo "$SERVICE_NAME stopped ...";  
          rm $PID_PATH_NAME     
          echo "$SERVICE_NAME starting ..."  
          nohup java -jar -Dspring.profiles.active=production $PATH_TO_JAR --server.port=$SERVER_PORT /tmp 2>> /dev/null >>/dev/null &            
          echo $! > $PID_PATH_NAME  
          echo "$SERVICE_NAME started ..."    
     else           
          echo "$SERVICE_NAME is not running ..."    
          fi     ;;
esac