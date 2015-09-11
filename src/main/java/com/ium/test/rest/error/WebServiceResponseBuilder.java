package com.ium.test.rest.error;

import com.ium.test.rest.wsobjects.WsErrorMessage;
import com.ium.test.rest.wsobjects.WsErrorResponse;
import com.ium.test.rest.wsobjects.WsMessages;
import com.ium.test.rest.wsobjects.WsRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ContainerRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.google.common.base.Joiner;

/**
 * <b>Description:</b> Responsible for creating a wrapped webservice response.
 */
@Component
@Slf4j
public class WebServiceResponseBuilder {
  public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

  /** Returns true if the response is an errorResponse, else false. */
  public boolean isErrorResponse(ContainerResponseContext containerResponseContext) {
    Response.Status.Family family = containerResponseContext.getStatusInfo().getFamily();
    return family == Response.Status.Family.SERVER_ERROR
        || family == Response.Status.Family.CLIENT_ERROR;
  }

  /** Creates a errorResponse webservice object. */
  public WsErrorResponse createErrorResponse(ContainerRequestContext containerRequestContext,
      ContainerResponseContext containerResponseContext) {
    WsErrorResponse errorResponse = new WsErrorResponse();
    errorResponse.setRequest(createRequest(containerRequestContext));
    errorResponse.setStatus(containerResponseContext.getStatus());
    errorResponse.setMessages(transformResponseEntityToMessage(containerResponseContext));
    return errorResponse;
  }

  private WsMessages transformResponseEntityToMessage(
      ContainerResponseContext containerResponseContext) {
    Object entity = containerResponseContext.getEntity();

    if (entity instanceof WsMessages) {
      return (WsMessages) entity;
    } else {
      log.error(
          "Could not create webService errorResponseMessage. Reason: Entity in containerResponse is not an instanceof WsMessages. {}",
          entity);
      WsMessages messages = new WsMessages();

      WsErrorMessage errorMessage = new WsErrorMessage();
      errorMessage.setCode(INTERNAL_SERVER_ERROR);
      errorMessage.setMessage(INTERNAL_SERVER_ERROR);

      messages.setErrors(Collections.singletonList(errorMessage));

      return messages;
    }
  }

  private WsRequest createRequest(ContainerRequestContext containerRequestContext) {
    Assert.isInstanceOf(ContainerRequest.class, containerRequestContext);
    ContainerRequest containerRequest = (ContainerRequest) containerRequestContext.getRequest();

    WsRequest request = new WsRequest();
    request.setMethod(containerRequest.getMethod());
    request.setPath(containerRequest.getPath(true));
    request.setUrl(containerRequest.getRequestUri().toString());

    if (containerRequest.getUriInfo() != null) {
      request.setParameters(transformToMap(containerRequest.getUriInfo().getQueryParameters()));
    }
    request.setHeaders(transformToMap(containerRequest.getRequestHeaders()));

    setRequestBody(containerRequestContext, request);
    return request;
  }

  private void setRequestBody(ContainerRequestContext containerRequestContext, WsRequest request) {
    try (OutputStream bodyProxyOutputStream = (OutputStream) containerRequestContext.
        getProperty(RequestProxyInterceptor.BODY_PROXY_OUTPUT_STREAM)) {

      if (bodyProxyOutputStream != null) {
        request.setBody(bodyProxyOutputStream.toString());
      }
    } catch (IOException e) {
      log.warn("An error has occurred while handling the body proxy output stream", e);
    }
  }

  private List<WsRequest.Entry> transformToMap(MultivaluedMap<String, String> multivaluedMap) {
    List<WsRequest.Entry> list = new ArrayList<>();
    for (Entry<String, List<String>> entry : multivaluedMap.entrySet()) {
      WsRequest.Entry listEntry = new WsRequest.Entry();
      listEntry.setKey(entry.getKey());
      listEntry.setValue(Joiner.on(", ").join(entry.getValue()));
      list.add(listEntry);
    }
    return list;
  }
}
