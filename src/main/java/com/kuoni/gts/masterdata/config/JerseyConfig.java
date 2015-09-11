package com.kuoni.gts.masterdata.config;

import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    packages("com.kuoni.gts.masterdata.rest");
    register(GZipEncoder.class);
  }
}
