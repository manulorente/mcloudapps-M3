# Serverless

## AWS profile

AWS user profile must grant below permission policies:

- AmazonAPIGatewayAdministrator
- AmazonEC2FullAccess
- AmazonS3FullAccess
- AmazonDynamoDBFullAcess
- AWSCloudFormationFullAccess
- AWSLambda_FullAccess
- IAMFullAccess

Credentias file must be stored in `C:\Users\%USERNAME%\.aws\` with below content:

    [%AWS_PROFILE_NAME%]
    aws_access_key_id = XXXXXXXXXXXXXXXXXXXX
    aws_secret_access_key = XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

## Steps

1. Create template app using SAM CLI

    ```bash
   sam init
    ```

2. Modify structure and code as needed

3. Build the app before deployment

    ```bash
   sam build
    ```

4. Launch DynamoDB

    ```bash
    docker run -p 8000:8000 amazon/dynamodb-local
    ```

6. Create table books

   ```bash
     aws dynamodb create-table --table-name books --attribute-definitions AttributeName=bookid,AttributeType=S --key-schema AttributeName=bookid,KeyType=HASH --billing-mode PAY_PER_REQUEST --endpoint-url http://127.0.0.1:8000
   ```

7. Create table reviews

   ```bash
     aws dynamodb create-table --table-name reviews --attribute-definitions AttributeName=reviewid,AttributeType=S --key-schema AttributeName=reviewid,KeyType=HASH --billing-mode PAY_PER_REQUEST --endpoint-url http://127.0.0.1:8000
   ```

8. Launch app in local

    ```bash
    sam local start-api 
    ```

9. Check app functionality with Postman

10. Deploy app in AWS

    ```bash
    sam deploy --guided
    ```

11. Once all endpoints have been tested, the stack must be deleted manually.

    ```bash
    aws cloudformation delete-stack  --stack-name sam-app --region us-east-1
    ```
