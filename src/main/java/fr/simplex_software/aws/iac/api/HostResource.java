package fr.simplex_software.aws.iac.api;

import jakarta.enterprise.context.*;
import jakarta.ws.rs.*;

import java.io.*;
import java.net.*;

@Path("host")
@ApplicationScoped
public class HostResource
{
  private static final String FMT = "*** My IP address is %s";

  @GET
  public String host() throws IOException
  {
    return String.format(FMT, InetAddress.getLocalHost().getHostAddress());
  }
}
