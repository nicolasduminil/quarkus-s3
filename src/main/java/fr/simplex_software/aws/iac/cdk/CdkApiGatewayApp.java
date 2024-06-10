package fr.simplex_software.aws.iac.cdk;

import io.quarkus.runtime.*;
import jakarta.enterprise.context.*;
import jakarta.enterprise.inject.*;
import jakarta.inject.*;
import software.amazon.awscdk.*;

@ApplicationScoped
public class CdkApiGatewayApp implements QuarkusApplication
{
  @Produces
  App app = new App();
  @Produces
  StackProps stackProps = StackProps.builder().build();
  CdkApiGatewayStack cdkApiGatewayStack;

  @Inject
  public CdkApiGatewayApp (CdkApiGatewayStack cdkApiGatewayStack)
  {
    this.cdkApiGatewayStack = cdkApiGatewayStack;
  }

  @Override
  public int run(String... args) throws Exception
  {
    Tags.of(app).add("project", "API Gateway with Quarkus");
    Tags.of(app).add("environment", "development");
    Tags.of(app).add("application", "CdkApiGatewayApp");
    app.synth();
    return 0;
  }
}
