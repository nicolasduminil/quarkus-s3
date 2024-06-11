package fr.simplex_software.aws.iac.cdk;

import io.quarkus.runtime.*;
import jakarta.enterprise.context.*;
import jakarta.enterprise.inject.*;
import jakarta.inject.*;
import software.amazon.awscdk.*;

@ApplicationScoped
public class CdkApiGatewayApp implements QuarkusApplication
{
  CdkApiGatewayStack cdkApiGatewayStack;
  private App app;

  @Inject
  public CdkApiGatewayApp (App app, CdkApiGatewayStack cdkApiGatewayStack)
  {
    this.app = app;
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
