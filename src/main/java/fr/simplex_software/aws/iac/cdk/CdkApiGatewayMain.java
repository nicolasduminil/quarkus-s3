package fr.simplex_software.aws.iac.cdk;

import io.quarkus.runtime.*;
import io.quarkus.runtime.annotations.*;

@QuarkusMain
public class CdkApiGatewayMain
{
  public static void main(String... args)
  {
    Quarkus.run(CdkApiGatewayApp.class, args);
  }
}
