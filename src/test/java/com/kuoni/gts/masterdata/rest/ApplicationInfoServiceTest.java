package com.kuoni.gts.masterdata.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Test;

public class ApplicationInfoServiceTest {

  @Test
  public void testGetApplicationInfo() throws Exception {
    ApplicationInfoService applicationInfoService = new ApplicationInfoService();
    Response response = applicationInfoService.getApplicationInfo();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }
}