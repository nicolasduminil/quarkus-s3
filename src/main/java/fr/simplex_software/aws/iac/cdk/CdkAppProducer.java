package fr.simplex_software.aws.iac.cdk;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

@ApplicationScoped
public class CdkAppProducer
{
  @Produces
  @Singleton
  public App app()
  {
    return new App();
  }

  @Produces
  @Singleton
  public StackProps stackProps()
  {
    return StackProps.builder().build();
  }
}
