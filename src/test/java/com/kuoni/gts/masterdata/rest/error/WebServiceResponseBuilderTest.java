package com.kuoni.gts.masterdata.rest.error;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ExtendedUriInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.kuoni.services.domain.v1_0.WsErrorMessage;
import com.kuoni.services.domain.v1_0.WsErrorResponse;
import com.kuoni.services.domain.v1_0.WsMessages;

@RunWith(MockitoJUnitRunner.class)
public class WebServiceResponseBuilderTest {

  private static final String LIST_PATH = "/list";
  private static final String LIST_CODE = "102";
  private static final String LIST_PUT_BODY = "<list xmlns=\"http://com.kuoni.services.domain\"></list>";
  private static final String BAD_REQUEST = "BadRequest";
  private WebServiceResponseBuilder webServiceResponseBuilder = new WebServiceResponseBuilder();

  @Test
  public void testIsErrorResponseWithNoError() throws Exception {
    ContainerResponseContext containerResponseContext = mock(ContainerResponseContext.class);
    when(containerResponseContext.getStatusInfo()).thenReturn(Response.Status.OK);

    boolean errorResponse = webServiceResponseBuilder.isErrorResponse(containerResponseContext);
    assertFalse(errorResponse);
  }

  @Test
  public void testIsErrorResponseWithServerError() throws Exception {
    ContainerResponseContext containerResponseContext = mock(ContainerResponseContext.class);
    when(containerResponseContext.getStatusInfo()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR);

    boolean errorResponse = webServiceResponseBuilder.isErrorResponse(containerResponseContext);
    assertTrue(errorResponse);
  }

  @Test
  public void testIsErrorResponseWithClientError() throws Exception {
    ContainerResponseContext containerResponseContext = mock(ContainerResponseContext.class);
    when(containerResponseContext.getStatusInfo()).thenReturn(Response.Status.BAD_REQUEST);

    boolean errorResponse = webServiceResponseBuilder.isErrorResponse(containerResponseContext);
    assertTrue(errorResponse);
  }

  @Test
  public void testCreateErrorResponseForGetRequest() throws Exception {
    ContainerRequest containerRequestContext = mock(ContainerRequest.class);
    ContainerRequest containerRequest = mock(ContainerRequest.class);
    when(containerRequestContext.getRequest()).thenReturn(containerRequest);
    when(containerRequest.getMethod()).thenReturn(HttpMethod.GET);
    when(containerRequest.getPath(anyBoolean())).thenReturn(LIST_PATH);
    when(containerRequest.getRequestUri()).thenReturn(new URI(LIST_PATH));

    MultivaluedMap<String, String> queryParams = new MultivaluedStringMap();
    queryParams.putSingle("referenceNumber", LIST_CODE);
    ExtendedUriInfo uriInfo = mock(ExtendedUriInfo.class);
    when(containerRequest.getUriInfo()).thenReturn(uriInfo);
    when(uriInfo.getQueryParameters()).thenReturn(queryParams);

    MultivaluedMap<String, String> headers = new MultivaluedStringMap();
    headers.putSingle("Content-Type", "application/vnd.list.v01.00+xml");
    when(containerRequest.getRequestHeaders()).thenReturn(headers);
    ContainerResponseContext containerResponseContext = mock(ContainerResponseContext.class);
    when(containerResponseContext.getStatus()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    WsErrorResponse wsErrorResponse = webServiceResponseBuilder.createErrorResponse(
            containerRequestContext, containerResponseContext);

    assertEquals(wsErrorResponse.getStatus(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    assertNotNull(wsErrorResponse.getRequest());
    assertEquals(wsErrorResponse.getRequest().getMethod(), HttpMethod.GET);
    assertEquals(wsErrorResponse.getRequest().getPath(), LIST_PATH);
    assertEquals(wsErrorResponse.getRequest().getUrl(), LIST_PATH);
    assertNull(wsErrorResponse.getRequest().getBody());

    assertTrue(wsErrorResponse.getRequest().getParameters().size() == 1);
    assertEquals(wsErrorResponse.getRequest().getParameters().get(0).getKey(), "referenceNumber");
    assertEquals(wsErrorResponse.getRequest().getParameters().get(0).getValue(),
            LIST_CODE);

    assertTrue(wsErrorResponse.getRequest().getHeaders().size() == 1);
    assertEquals(wsErrorResponse.getRequest().getHeaders().get(0).getKey(), "Content-Type");
    assertEquals(wsErrorResponse.getRequest().getHeaders().get(0).getValue(),
            "application/vnd.list.v01.00+xml");

    assertNotNull(wsErrorResponse.getMessages());
    assertNotNull(wsErrorResponse.getMessages().getErrors());
    assertTrue(wsErrorResponse.getMessages().getErrors().size() == 1);
    assertEquals(wsErrorResponse.getMessages().getErrors().get(0).getCode(),
            WebServiceResponseBuilder.INTERNAL_SERVER_ERROR);
    assertEquals(wsErrorResponse.getMessages().getErrors().get(0).getMessage(),
            WebServiceResponseBuilder.INTERNAL_SERVER_ERROR);
  }

  @Test
  public void testCreateErrorResponseForPutRequest() throws Exception {
    ContainerRequest containerRequestContext = mock(ContainerRequest.class);
    ContainerRequest containerRequest = mock(ContainerRequest.class);
    when(containerRequestContext.getRequest()).thenReturn(containerRequest);
    MultivaluedMap<String, String> requestHeaders = new MultivaluedStringMap();
    requestHeaders.add("1", "1");
    when(containerRequest.getRequestHeaders()).thenReturn(requestHeaders);
    when(containerRequest.getMethod()).thenReturn(HttpMethod.PUT);
    when(containerRequest.getPath(anyBoolean())).thenReturn(LIST_PATH);
    when(containerRequest.getRequestUri()).thenReturn(new URI(LIST_PATH));

    OutputStream outputStream = mock(OutputStream.class);
    when(containerRequestContext.
            getProperty(RequestProxyInterceptor.BODY_PROXY_OUTPUT_STREAM)).thenReturn(outputStream);
    when(outputStream.toString()).thenReturn(LIST_PUT_BODY);

    ContainerResponseContext containerResponseContext = mock(ContainerResponseContext.class);
    when(containerResponseContext.getStatus()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    WsMessages wsMessages = new WsMessages();
    List<WsErrorMessage> wsErrorMessages = new ArrayList<>();
    WsErrorMessage wsErrorMessage = new WsErrorMessage();
    wsErrorMessage.setCode(BAD_REQUEST);
    wsErrorMessage.setMessage(BAD_REQUEST);
    wsErrorMessages.add(wsErrorMessage);
    wsMessages.setErrors(wsErrorMessages);
    when(containerResponseContext.getEntity()).thenReturn(wsMessages);

    WsErrorResponse wsErrorResponse = webServiceResponseBuilder.createErrorResponse(
            containerRequestContext, containerResponseContext);

    assertEquals(wsErrorResponse.getStatus(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    assertNotNull(wsErrorResponse.getRequest());
    assertEquals(wsErrorResponse.getRequest().getMethod(), HttpMethod.PUT);
    assertEquals(wsErrorResponse.getRequest().getPath(), LIST_PATH);
    assertEquals(wsErrorResponse.getRequest().getUrl(), LIST_PATH);
    assertNotNull(wsErrorResponse.getRequest().getBody());
    assertEquals(wsErrorResponse.getRequest().getBody(), LIST_PUT_BODY);

    assertNotNull(wsErrorResponse.getMessages());
    assertNotNull(wsErrorResponse.getMessages().getErrors());
    assertTrue(wsErrorResponse.getMessages().getErrors().size() == 1);
    assertEquals(wsErrorResponse.getMessages().getErrors().get(0).getCode(),
            BAD_REQUEST);
    assertEquals(wsErrorResponse.getMessages().getErrors().get(0).getMessage(),
            BAD_REQUEST);
  }

  @Test
  public void testCreateErrorResponseForPutRequestWithIOExceptionOnRequestBodyOutputStream() throws Exception {
    ContainerRequest containerRequestContext = mock(ContainerRequest.class);
    ContainerRequest containerRequest = mock(ContainerRequest.class);
    when(containerRequestContext.getRequest()).thenReturn(containerRequest);
    MultivaluedMap<String, String> requestHeaders = new MultivaluedStringMap();
    requestHeaders.add("1", "1");
    when(containerRequest.getRequestHeaders()).thenReturn(requestHeaders);
    when(containerRequest.getMethod()).thenReturn(HttpMethod.PUT);
    when(containerRequest.getPath(anyBoolean())).thenReturn(LIST_PATH);
    when(containerRequest.getRequestUri()).thenReturn(new URI(LIST_PATH));

    doThrow(IOException.class).when(containerRequestContext).
            getProperty(RequestProxyInterceptor.BODY_PROXY_OUTPUT_STREAM);

    ContainerResponseContext containerResponseContext = mock(ContainerResponseContext.class);
    when(containerResponseContext.getStatus()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    WsErrorResponse wsErrorResponse = webServiceResponseBuilder.createErrorResponse(
            containerRequestContext, containerResponseContext);

    assertEquals(wsErrorResponse.getStatus(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    assertNotNull(wsErrorResponse.getRequest());
    assertNull(wsErrorResponse.getRequest().getBody());
  }

  @Test
  public void testCreateErrorResponseForPutRequestWithNullRequestBodyOutputStream() throws Exception {
    ContainerRequest containerRequestContext = mock(ContainerRequest.class);
    ContainerRequest containerRequest = mock(ContainerRequest.class);
    when(containerRequestContext.getRequest()).thenReturn(containerRequest);
    MultivaluedMap<String, String> requestHeaders = new MultivaluedStringMap();
    requestHeaders.add("1", "1");
    when(containerRequest.getRequestHeaders()).thenReturn(requestHeaders);
    when(containerRequest.getMethod()).thenReturn(HttpMethod.PUT);
    when(containerRequest.getPath(anyBoolean())).thenReturn(LIST_PATH);
    when(containerRequest.getRequestUri()).thenReturn(new URI(LIST_PATH));

    when(containerRequestContext.getProperty(RequestProxyInterceptor.BODY_PROXY_OUTPUT_STREAM)).thenReturn(null);

    ContainerResponseContext containerResponseContext = mock(ContainerResponseContext.class);
    when(containerResponseContext.getStatus()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    WsErrorResponse wsErrorResponse = webServiceResponseBuilder.createErrorResponse(
            containerRequestContext, containerResponseContext);

    assertEquals(wsErrorResponse.getStatus(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    assertNotNull(wsErrorResponse.getRequest());
    assertNull(wsErrorResponse.getRequest().getBody());
  }
}