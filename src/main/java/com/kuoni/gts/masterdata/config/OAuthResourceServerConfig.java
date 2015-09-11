package com.kuoni.gts.masterdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Autowired
  private TokenStore tokenStore;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId("techdev-services").tokenStore(tokenStore);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.requestMatchers()
            .regexMatchers(".*")
            .and()
            .authorizeRequests()
            .antMatchers("/manage/**", "/application.wadl").permitAll()
            .regexMatchers(HttpMethod.POST, "/list/search.*").hasRole("LIST_READ")
            .regexMatchers(HttpMethod.GET, "/list.*").hasRole("LIST_READ")
            .regexMatchers(HttpMethod.PUT, "/list.*").hasRole("LIST_WRITE")
            .regexMatchers(HttpMethod.DELETE, "/list.*").hasRole("LIST_DELETE")
            .regexMatchers(".*").denyAll()
            .and()
            .csrf().disable();
  }
}
