package fr.simplex_software.aws.iac.cdk;

import jakarta.inject.*;
import org.eclipse.microprofile.config.inject.*;
import software.amazon.awscdk.*;
import software.amazon.awscdk.aws_apigatewayv2_integrations.*;
import software.amazon.awscdk.services.apigatewayv2.*;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.*;

@Singleton
public class CdkApiGatewayStack extends Stack
{
  @Inject
  @ConfigProperty(name = "cdk.handler", defaultValue = "io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest")
  String handler;
  @ConfigProperty(name = "cdk.memory", defaultValue = "128")
  int ram;
  @ConfigProperty(name="cdk.time-out", defaultValue = "10")
  int timeOut;
  @ConfigProperty(name = "cdk.function-name", defaultValue = "QuarkusApiGatewayLambda")
  String lambdaFunction;
  @ConfigProperty(name = "cdk.function-id", defaultValue = "quarkus-api-gateway-lambda")
  String id;

  @Inject
  public CdkApiGatewayStack(final App scope, @ConfigProperty(name = "cdk.stack-id", defaultValue = "QuarkusStack") final String stackId, final StackProps props)
  {
    super(scope, stackId, props);
  }

  public void initStack()
  {
    IFunction function = Function.Builder.create(this, id)
      .runtime(Runtime.JAVA_21)
      .handler(handler)
      .memorySize(ram)
      .timeout(Duration.seconds(timeOut))
      .functionName(lambdaFunction)
      .code(Code.fromAsset((String)this.getNode().tryGetContext("zip")))
      .build();
    FunctionUrl functionUrl = function.addFunctionUrl(FunctionUrlOptions.builder().authType(FunctionUrlAuthType.NONE).build());
    String url = HttpApi.Builder.create(this, "HttpApiGatewayIntegration")
      .defaultIntegration(HttpLambdaIntegration.Builder.create("HttpApiGatewayIntegration", function).build()).build().getUrl();
    CfnOutput.Builder.create(this, "FunctionURLOutput").value(functionUrl.getUrl()).build();
    CfnOutput.Builder.create(this, "FunctionArnOutput").value(function.getFunctionArn()).build();
    CfnOutput.Builder.create(this, "HttpApiGatewayUrlOutput").value(url).build();
    CfnOutput.Builder.create(this, "HttpApiGatewayCurlOutput").value("curl -i " + url + "/s3").build();
  }
}
