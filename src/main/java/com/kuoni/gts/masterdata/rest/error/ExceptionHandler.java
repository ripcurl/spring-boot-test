package com.kuoni.gts.masterdata.rest.error;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;

import com.kuoni.services.domain.v1_0.WsErrorMessage;
import com.kuoni.services.domain.v1_0.WsMessages;

/**
 * <b>Description:</b> Responsible handling all exceptions happening during the processing of a
 * webservice call.
 */
@Provider
@Slf4j
public class ExceptionHandler implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable exception) {
    log.error("Error while processing webservice request. Creating error response.", exception);
    Response response;
    if (exception instanceof IllegalArgumentException) {
      response = createResponse(Status.BAD_REQUEST, "IllegalArgument", exception.getMessage(), exception);
    } else if (exception instanceof BadRequestException) {
      response = createResponse(Status.BAD_REQUEST, "BadRequestException", exception.getMessage(), exception);
    } else if (exception.getCause() instanceof ConstraintViolationException) {
      response = createResponse(Status.BAD_REQUEST, "ValidationError",
                      exception.getCause().getCause().getMessage(), exception);
    } else if (exception instanceof EntityNotFoundException) {
      response = createResponse(Status.NOT_FOUND, "EntityNotFound", exception.getMessage(), exception);
    } else if (exception instanceof OptimisticLockingFailureException) {
      response = createResponse(Status.CONFLICT, "OptimisticLockError", exception.getMessage(), exception);
    } else if (exception instanceof NotFoundException) {
      response = createResponse(Status.NOT_FOUND, "UrlNotFound", exception.getMessage(), exception);
    } else if (exception instanceof AccessDeniedException) {
      response = createResponse(Status.FORBIDDEN, "AccessDenied", exception.getMessage(), exception);
    } else if (exception instanceof WebApplicationException) {
      Response rsp = ((WebApplicationException) exception).getResponse();
      response = createResponse(rsp.getStatusInfo(), rsp.getStatusInfo().getReasonPhrase(), exception.getMessage(), exception);
    } else {
      response = createResponse(Status.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
              exception.getMessage(), exception);
    }
    return response;
  }

  private Response createResponse(StatusType status, String code, String message,
                                  Throwable throwable) {
    WsMessages messages = new WsMessages();
    WsErrorMessage errorMessage = new WsErrorMessage();
    errorMessage.setCode(code);
    errorMessage.setMessage(message);
    errorMessage.setStacktrace(getStackTraceAsString(throwable));
    messages.setErrors(Collections.singletonList(errorMessage));
    return Response.status(status).type(MediaType.APPLICATION_XHTML_XML)
            .entity(messages).build();
  }

  private String getStackTraceAsString(Throwable throwable) {
    StringWriter errors = new StringWriter();
    throwable.printStackTrace(new PrintWriter(errors)); //NOSONAR
    return errors.toString();
  }
}
