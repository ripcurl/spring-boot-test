package com.kuoni.gts.masterdata.rest.error;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import org.apache.commons.io.input.TeeInputStream;

/** Request Interceptor proxy */
@Provider
public class RequestProxyInterceptor implements ReaderInterceptor {
  public static final String BODY_PROXY_OUTPUT_STREAM = "bodyProxyOutputStream";

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException {
    ByteArrayOutputStream proxyOutputStream = new ByteArrayOutputStream();
    context.setInputStream(new TeeInputStream(context.getInputStream(), proxyOutputStream));
    context.setProperty(BODY_PROXY_OUTPUT_STREAM, proxyOutputStream);
    return context.proceed();
  }
}
