package com.kuoni.gts.masterdata.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kuoni.services.domain.v1_0.WsApplicationInfo;

/** This class represents application information rest service */
@Path("/applicationInfo")
@Component
public class ApplicationInfoService {

  @Value("${info.version}")
  private String version;

  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Response getApplicationInfo() throws IOException {
    WsApplicationInfo info = new WsApplicationInfo();
    info.setVersion(version);
    return Response.ok(info).build();
  }
}
