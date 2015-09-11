package com.kuoni.gts.masterdata.rest.error;


import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;

public class ExceptionHandlerTest {
  private ExceptionHandler exceptionHandler = new ExceptionHandler();

  @Test
  public void testIllegalArgumentException() throws Exception {
    IllegalArgumentException exception = mock(IllegalArgumentException.class);

    Response response = exceptionHandler.toResponse(exception);

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testBadRequestException() throws Exception {
    BadRequestException exception = mock(BadRequestException.class);

    Response response = exceptionHandler.toResponse(exception);

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testConstraintViolationException() throws Exception {
    DataIntegrityViolationException dataIntegrityViolationException = mock(DataIntegrityViolationException.class);
    ConstraintViolationException violationException = mock(ConstraintViolationException.class);
    ConstraintViolationException exception = mock(ConstraintViolationException.class);
    when(exception.getMessage()).thenReturn("violationException");
    when(violationException.getCause()).thenReturn(exception);
    when(dataIntegrityViolationException.getCause()).thenReturn(violationException);

    Response response = exceptionHandler.toResponse(dataIntegrityViolationException);

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testEntityNotFoundException() throws Exception {
    EntityNotFoundException exception = mock(EntityNotFoundException.class);

    Response response = exceptionHandler.toResponse(exception);

    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }

  @Test
  public void testOptimisticLockingFailureException() throws Exception {
    OptimisticLockingFailureException exception = mock(OptimisticLockingFailureException.class);

    Response response = exceptionHandler.toResponse(exception);

    assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
  }

  @Test
  public void testNotFoundException() throws Exception {
    NotFoundException exception = mock(NotFoundException.class);

    Response response = exceptionHandler.toResponse(exception);

    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }

  @Test
  public void testAccessDeniedException() throws Exception {
    AccessDeniedException exception = mock(AccessDeniedException.class);
    Response response = exceptionHandler.toResponse(exception);

    assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
  }

  @Test
  public void testWebApplicationException() throws Exception {
    WebApplicationException exception = mock(WebApplicationException.class);
    Response rsp = mock(Response.class);
    Response.StatusType statusInfo = mock(Response.StatusType.class);
    when(statusInfo.getReasonPhrase()).thenReturn("Validation");
    when(statusInfo.getStatusCode()).thenReturn(400);
    when(rsp.getStatusInfo()).thenReturn(statusInfo);
    when(exception.getResponse()).thenReturn(rsp);

    Response response = exceptionHandler.toResponse(exception);

    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void testNullPointerException() throws Exception {
    NullPointerException exception = mock(NullPointerException.class);

    Response response = exceptionHandler.toResponse(exception);

    assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
  }

}