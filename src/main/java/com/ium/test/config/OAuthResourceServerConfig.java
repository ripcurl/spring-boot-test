package com.ium.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId("restservice");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.requestMatchers()
            .regexMatchers(".*")
            .and()
            .authorizeRequests()
            .antMatchers("/manage/**", "/application.wadl", "/applicationInfo", "/uaa/**").permitAll()
            .regexMatchers(HttpMethod.POST, "/list/search.*").hasRole("LIST_READ")
            .regexMatchers(HttpMethod.GET, "/list.*").hasRole("LIST_READ")
            .regexMatchers(HttpMethod.PUT, "/list.*").hasRole("LIST_WRITE")
            .regexMatchers(HttpMethod.DELETE, "/list.*").hasRole("LIST_DELETE")
            .regexMatchers(".*").denyAll()
            .and()
            .csrf().disable();
  }
}
