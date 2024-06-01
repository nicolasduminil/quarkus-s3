#!/bin/sh
mvn -DskipITs -Dfr.simplex_software.aws.iac.quarkus.s3.tests.S3FileManagementTestClient/mp-rest/url=http://localhost:8081 clean install
cdk deploy --all --context zip=target/function.zip --require-approval=never
API_ENDPOINT=$(aws cloudformation describe-stacks --stack-name QuarkusApiGatewayStack --query 'Stacks[0].Outputs[0].OutputValue' --output text)
mvn -Dfr.simplex_software.aws.iac.quarkus.s3.tests.S3FileManagementTestClient/mp-rest/url=$API_ENDPOINT failsafe:integration-test
