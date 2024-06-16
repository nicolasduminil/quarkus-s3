package fr.simplex_software.aws.iac.cdk;

import jakarta.inject.*;
import org.eclipse.microprofile.config.inject.*;
import software.amazon.awscdk.*;
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
    if (handler == null)
      System.out.println (">>> Handler is null");
    else
      System.out.println (">>> Handlet is not null");
    IFunction function = Function.Builder.create(this, id)
      .runtime(Runtime.JAVA_21)
      .handler(handler)
      .memorySize(ram)
      .timeout(Duration.seconds(timeOut))
      .functionName(lambdaFunction)
      .code(Code.fromAsset((String)this.getNode().tryGetContext("zip")))
      .build();
  }
}
