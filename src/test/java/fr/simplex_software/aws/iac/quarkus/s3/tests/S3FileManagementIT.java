package fr.simplex_software.aws.iac.quarkus.s3.tests;

import fr.simplex_software.aws.iac.quarkus.s3.*;
import io.quarkus.test.junit.*;
import jakarta.inject.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.rest.client.inject.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class S3FileManagementIT
{
  private static File readme = new File("./src/test/resources/README.md");
  @Inject
  @RestClient
  S3FileManagementTestClient s3FileManagementTestClient;

  @Test
  @Order(10)
  public void testUploadFile() throws Exception
  {
    Response response = s3FileManagementTestClient.uploadFile(new FileMetadata(readme, "README.md", MediaType.TEXT_PLAIN));
    assertThat(response).isNotNull();
    assertThat(response.getStatusInfo().toEnum()).isEqualTo(Response.Status.CREATED);
  }

}
