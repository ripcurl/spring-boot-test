package com.kuoni.gts.masterdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
  @Bean
  public InMemoryTokenStore tokenStore() {
    return new InMemoryTokenStore();
  }

  @Bean
  protected AuthorizationCodeServices authorizationCodeServices() {
    return new InMemoryAuthorizationCodeServices();
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authorizationCodeServices(new InMemoryAuthorizationCodeServices())
        .tokenStore(tokenStore()).approvalStoreDisabled();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("trackr-page")
        .resourceIds("techdev-services")
        .authorizedGrantTypes("implicit")
        .authorities("ROLE_LIST_READ", "ROLE_LIST_WRITE", "ROLE_LIST_DELETE")
        .scopes("read", "write")
        .redirectUris("https://github.com/ripcurl");


//        .withClient("curl")
//        .resourceIds("list")
//        .scopes("read", "write")
//        .authorizedGrantTypes("client_credentials")
//        .secret("password")
//        .and()
//        .withClient("web")
//        .redirectUris("http://github.com/techdev-solutions/")
//        .resourceIds("list")
//        .scopes("read")
//        .authorizedGrantTypes("implicit");
  }
}
