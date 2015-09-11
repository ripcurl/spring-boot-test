package com.kuoni.gts.masterdata.rest.error;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <b>Description:</b> Responsible for wrapping webservice errorResponses.
 */
@Provider
public class ErrorResponseWrapperFilter implements ContainerResponseFilter {
  @Autowired
  private WebServiceResponseBuilder webServiceResponseBuilder;

  @Override
  public void filter(ContainerRequestContext containerRequestContext,
                     ContainerResponseContext containerResponseContext) throws IOException {
    if (webServiceResponseBuilder.isErrorResponse(containerResponseContext)) {
      containerResponseContext.setEntity(
              webServiceResponseBuilder.createErrorResponse(containerRequestContext, containerResponseContext),
              containerResponseContext.getEntityAnnotations(),
              MediaType.valueOf("application/vnd.errorResponse.v01.00+xml"));
    }
  }

}
