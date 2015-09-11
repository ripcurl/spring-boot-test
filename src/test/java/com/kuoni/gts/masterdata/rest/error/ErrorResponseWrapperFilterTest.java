package com.kuoni.gts.masterdata.rest.error;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ErrorResponseWrapperFilterTest {

  @InjectMocks
  private ErrorResponseWrapperFilter errorResponseWrapperFilter;

  @Mock
  private WebServiceResponseBuilder webServiceResponseBuilder;

  private ContainerRequestContext containerRequestContext = mock(ContainerRequestContext.class);
  private ContainerResponseContext containerResponseContext = mock(ContainerResponseContext.class);

  @Test
  public void testFilterWithErrorResponse() throws Exception {
    when(webServiceResponseBuilder.isErrorResponse(containerResponseContext)).thenReturn(true);

    errorResponseWrapperFilter.filter(containerRequestContext, containerResponseContext);
    verify(webServiceResponseBuilder).createErrorResponse(containerRequestContext, containerResponseContext);
  }

  @Test
  public void testFilterWithNoErrorResponse() throws Exception {
    when(webServiceResponseBuilder.isErrorResponse(containerResponseContext)).thenReturn(false);

    errorResponseWrapperFilter.filter(containerRequestContext, containerResponseContext);
    verify(webServiceResponseBuilder).isErrorResponse(containerResponseContext);
    verifyNoMoreInteractions(webServiceResponseBuilder);
  }
}