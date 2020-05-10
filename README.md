# SpringBoot2DemoForAwsLambda serverless API
The SpringBoot2DemoForAwsLambda project, created with [`aws-serverless-java-container`](https://github.com/awslabs/aws-serverless-java-container).

The starter project defines a simple `/ping` resource that can accept `GET` requests with its tests.

The project folder also includes a `template.yml` file. You can use this [SAM](https://github.com/awslabs/serverless-application-model) file to deploy the project to AWS Lambda and Amazon API Gateway or test in local with the [SAM CLI](https://github.com/awslabs/aws-sam-cli). 

## Pre-requisites
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Gradle](https://gradle.org/) or [Maven](https://maven.apache.org/)

## Building the project
You can use the SAM CLI to quickly build the project
```bash
$ mvn archetype:generate -DartifactId=SpringBoot2DemoForAwsLambda -DarchetypeGroupId=com.amazonaws.serverless.archetypes -DarchetypeArtifactId=aws-serverless-jersey-archetype -DarchetypeVersion=1.5 -DgroupId=com.example.training -Dversion=1.0-SNAPSHOT -Dinteractive=false
$ cd SpringBoot2DemoForAwsLambda
$ sam build
Building resource 'SpringBoot2DemoForAwsLambdaFunction'
Running JavaGradleWorkflow:GradleBuild
Running JavaGradleWorkflow:CopyArtifacts

Build Succeeded

Built Artifacts  : .aws-sam/build
Built Template   : .aws-sam/build/template.yaml

Commands you can use next
=========================
[*] Invoke Function: sam local invoke
[*] Deploy: sam deploy --guided
```

## Testing locally with the SAM CLI

From the project root folder - where the `template.yml` file is located - start the API with the SAM CLI.

```bash
$ sam local start-api

...
Mounting com.amazonaws.serverless.archetypes.StreamLambdaHandler::handleRequest (java8) at http://127.0.0.1:3000/{proxy+} [OPTIONS GET HEAD POST PUT DELETE PATCH]
...
```

Using a new shell, you can send a test ping request to your API:

```bash
$ curl -s http://127.0.0.1:3000/ping | python -m json.tool

{
    "pong": "Hello, World!"
}
``` 

## Deploying to AWS
To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

```
$ sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use `curl` or a web browser to make a call to the URL

```
...
-------------------------------------------------------------------------------------------------------------
OutputKey-Description                        OutputValue
-------------------------------------------------------------------------------------------------------------
SpringBoot2DemoForAwsLambdaApi - URL for application            https://xxxxxxxxxx.execute-api.us-west-2.amazonaws.com/Prod/pets
-------------------------------------------------------------------------------------------------------------
```

Copy the `OutputValue` into a browser or use curl to test your first request:

```bash
$ curl -s https://xxxxxxx.execute-api.us-west-2.amazonaws.com/Prod/ping | python -m json.tool

{
    "pong": "Hello, World!"
}
```


## BUILDING AND DEPLOYMENT TO S3 WITH MAVEN

1- Run clean and package Maven tasks
```
$ mvn clean package
```
2- Create a S3 bucket to upload the generated package. Name must be unique. 
(Just run once to create, for next deployments, you can start in point 3)

```
$ aws s3 mb s3://springbootawstraining --region eu-west-3
```

3- Create cloud infrastructure with cloud formation, using the created bucket. This will also upload package to bucket.
```
$ aws cloudformation package --template-file template-maven.yml --output-template-file target/output-template-maven.yml --s3-bucket springbootawstraining
```

4- Deploy resources in CloudFormation and create stack
```
$ aws cloudformation deploy --template-file .\target\output-template-maven.yml --stack-name springbootawstrainingstack --region eu-west-3 --capabilities CAPABILITY_IAM
```
If above command fails, you can see logs running command:
```
$ aws cloudformation describe-stack-events --stack-name springbootawstrainingstack.
```
If error is ```You must specify a region```, region can be configured running :
```
$ aws configure
```
It will ask the aws_access_key_id and aws_secret_id, then run again ```aws configure``` command.


6- Describe the stack to get its information and endpoint link (URL for application)
```
$ aws cloudformation describe-stacks --stack-name springbootawstrainingstack --region eu-west-3
```

Generated output will shown an URL like this:
```
"https://62gl9de7i4.execute-api.eu-west-3.amazonaws.com",
```

Sources: 
https://medium.com/@anupam.ncsu/spring-boot-rest-api-on-aws-lambda-ca840e579d69ç
https://github.com/anupam-ncsu/springLambdaJavaAws/blob/lambda/sam.yaml
