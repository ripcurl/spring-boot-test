package com.ium.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDataSourceConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(profiles = "integrationTest")
public class ApplicationTests {
  @Autowired private ConfigurableApplicationContext context;

  @Test
  public void contextLoads() {
    assertTrue(context.isActive());
  }
}

@Configuration
@Profile("integrationTest")
@Import(EmbeddedDataSourceConfiguration.class)
class TestDataSource {
}

@Configuration
@Profile("integrationTest")
@EnableResourceServer
class TestOAuthResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.requestMatchers()
            .regexMatchers(".*")
            .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll();
  }
}