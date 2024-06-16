package fr.simplex_software.aws.iac.tests;

import fr.simplex_software.aws.iac.api.*;
import io.quarkus.test.junit.*;
import jakarta.inject.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.rest.client.inject.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class HostResourceIT
{
  @Inject
  @RestClient
  HostResourceClient hostResourceClient;
  @Inject
  @ConfigProperty(name = "base_uri/mp-rest/url")
  String baseURI;

  @Test
  public void testHostResource() throws IOException
  {
    assertThat(hostResourceClient.host()).contains("");
  }
}
