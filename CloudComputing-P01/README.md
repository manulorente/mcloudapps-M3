# Cloud Computing P01

## 1 - Create S3 bucket

- Bucket name: mca-events
- Region: us-east-1
- ACLs enabled + Object writer
- Allow all public access

## 2 - Create EC2 instance

- Ubuntu Server 18.04 LTS
- Free tier (t2.micro)

## 3 - Create security groups for EC2 instance

Inbound rules:

    22	    SSH                 my_ip	
    443	    HTTPS               0.0.0.0/0
    3306    MYSQL/Aurora        0.0.0.0/0

## 4 - Create IAM user with __S3FullAccess__ rol to access EC2 with AmazonS3FullAccess policy

## 5 - Create RDS instance

- MySQL 8.0.28
- Free Tier (db.t2.micro)
- USER: root
- PASS: 12345678
- DB:   events-db
- DB_NAME: events
- PORT: 3306

## 6 - Connect to EC2 instance via SSH - Need to be from local C: disk

UNIX
`chmod 400 your_key_name.pem.pem`

WIN
`icacls your_key_name.pem /reset`
`icacls your_key_name.pem /grant:r %username%:(R)`
`icacls your_key_name.pem /inheritance:r`

Local
`ssh -i your_key_name.pem ubuntu@ec2-35-175-132-90.compute-1.amazonaws.com`

EC2
`sudo apt-get update`
`sudo apt install -y openjdk-17-jdk`
`java -version`

## 7 - Package the app and copy jar file to remote host

Local
`scp -i your_key_name.pem m.lorente-j.garrido-0.0.1-SNAPSHOT.jar ubuntu@ec2-35-175-132-90.compute-1.amazonaws.com:/home/ubuntu/`

## 8 - Launch app

EC2

```sh
export RDS_DNS=events-db.ckbzargdvke9.us-east-1.rds.amazonaws.com
export RDS_DATABASE=events
export RDS_USER=root
export RDS_PASS=12345678
export SERVER_PORT=443
export BUCKET_NAME=mca-events
export REGION=us-east-1
export APP_NAME=m.lorente-j.garrido-0.0.1-SNAPSHOT.jar
sudo chmod +x ${APP_NAME}
sudo java -jar -Dspring.profiles.active=production ${APP_NAME} \
    --server.port=${SERVER_PORT} \
    --spring.datasource.url=jdbc:mysql://${RDS_DNS}/${RDS_DATABASE} \
    --spring.datasource.username=${RDS_USER} \
    --spring.datasource.password=${RDS_PASS} \
    --amazon.s3.bucket-name=${BUCKET_NAME} \
    --amazon.s3.endpoint=https://s3.amazonaws.com/${BUCKET_NAME}/ \
    --amazon.s3.region=${REGION}
```

## 9 -  Create service to autorestart the app with the instance

Local
```sh
scp -i your_key_name.pem events_service.sh ubuntu@ec2-35-175-132-90.compute-1.amazonaws.com:/tmp/events_service.sh
scp -i your_key_name.pem events_service.service ubuntu@ec2-35-175-132-90.compute-1.amazonaws.com:/tmp/events_service.service
```

EC2
```sh
sudo mv /tmp/events_service.sh /usr/local/bin/events_service.sh
sudo mv /tmp/events_service.service /etc/systemd/system/events_service.service
sudo chmod +x /usr/local/bin/events_service.sh
sudo systemctl daemon-reload
sudo systemctl enable events_service
sudo systemctl start events_service
```