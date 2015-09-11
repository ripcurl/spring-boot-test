package com.ium.test.rest.error;

import static org.mockito.Mockito.mock;

import javax.ws.rs.ext.ReaderInterceptorContext;

import org.junit.Test;

public class RequestProxyInterceptorTest {

  @Test
  public void testAroundReadFrom() throws Exception {
    RequestProxyInterceptor requestProxyInterceptor = new RequestProxyInterceptor();

    ReaderInterceptorContext context = mock(ReaderInterceptorContext.class);
    requestProxyInterceptor.aroundReadFrom(context);
  }
}