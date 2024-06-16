#!/bin/bash
mvn -DskipITs -Dbase_uri/mp-rest/url=http://localhost:8081 -Dquarkus.package.type=fast-jar clean install
cdk deploy --all --context zip=target/function.zip --require-approval=never
API_ENDPOINT=$(aws cloudformation describe-stacks --stack-name QuarkusApiGatewayStack --query "Stacks[0].Outputs[?OutputKey=='FunctionURLOutput'].OutputValue" --output text)
#API_ENDPOINT=$(aws cloudformation describe-stacks --stack-name QuarkusApiGatewayStack --query "Stacks[0].Outputs[?OutputKey=='HttpApiGatewayUrlOutput'].OutputValue" --output text)
mvn -Dbase_uri/mp-rest/url=$API_ENDPOINT failsafe:integration-test