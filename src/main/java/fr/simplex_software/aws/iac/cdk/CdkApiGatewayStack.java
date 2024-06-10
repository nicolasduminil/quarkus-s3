package fr.simplex_software.aws.iac.cdk;

import jakarta.enterprise.context.*;
import jakarta.inject.*;
import org.eclipse.microprofile.config.inject.*;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.s3.*;

@ApplicationScoped
public class CdkApiGatewayStack extends Stack
{
  @Inject
  public CdkApiGatewayStack(final App scope, @ConfigProperty(name = "cdk.stack-id", defaultValue = "QuarkusStack") final String stackId, final StackProps props)
  {
    super(scope, stackId, props);
    Bucket.Builder.create(this, "myBucket").build();
  }
}
