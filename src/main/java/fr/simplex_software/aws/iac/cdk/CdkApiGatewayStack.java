package fr.simplex_software.aws.iac.cdk;

import jakarta.enterprise.context.*;
import jakarta.inject.*;
import org.eclipse.microprofile.config.inject.*;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.s3.*;

@ApplicationScoped
public class CdkApiGatewayStack extends Stack
{
  private static final String HANDLER = "io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest";
  private static int RAM = 128;
  private static int TIME_OUT = 10;
  private static final String FUNCTION = "QuarkusApiGatewayLambda";
  private static final String ID = "quarkus-api-gateway-lambda";

  @Inject
  public CdkApiGatewayStack(final App scope, @ConfigProperty(name = "cdk.stack-id", defaultValue = "QuarkusApiGatewayStack") final String stackId, final StackProps props)
  {
    super(scope, stackId, props);
    Bucket.Builder.create(this, "myBucket").build();
  }
}
