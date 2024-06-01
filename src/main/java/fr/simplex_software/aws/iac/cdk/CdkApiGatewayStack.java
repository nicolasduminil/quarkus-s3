package fr.simplex_software.aws.iac.cdk;

import software.amazon.awscdk.*;
import software.amazon.awscdk.services.lambda.*;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.*;

import java.lang.*;

public class CdkApiGatewayStack extends Stack
{
  private static final String HANDLER = "io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest";
  private static int RAM = 128;
  private static int TIME_OUT = 10;
  private static final String FUNCTION = "QuarkusApiGatewayLambda";
  private static final String ID = "quarkus-api-gateway-lambda";

  public CdkApiGatewayStack(final Construct scope, final String stackId, final StackProps props)
  {
    super(scope, stackId, props);
    IFunction function = Function.Builder.create(this, ID)
      .runtime(Runtime.JAVA_21)
      .handler(HANDLER)
      .memorySize(RAM)
      .timeout(Duration.seconds(TIME_OUT))
      .functionName(FUNCTION)
      .code(Code.fromAsset((String)this.getNode().tryGetContext("zip")))
      .build();
    FunctionUrl functionUrl = function.addFunctionUrl(FunctionUrlOptions.builder().authType(FunctionUrlAuthType.NONE).build());
    CfnOutput.Builder.create(this, "FunctionURLOutput").value(functionUrl.getUrl()).build();
  }
}
