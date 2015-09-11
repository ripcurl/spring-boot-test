package com.ium.test.service.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RunWith(MockitoJUnitRunner.class)
public class SpringSecurityAuditorAwareTest {

  private static final String CURRENT_AUDITOR = "805535";

  private SpringSecurityAuditorAware springSecurityAuditorAware = new SpringSecurityAuditorAware();

  @Test
  public void testGetCurrentAuditorWithAnonymousUser() throws Exception {
    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication()).thenReturn(null);
    SecurityContextHolder.setContext(securityContext);

    String currentAuditor = springSecurityAuditorAware.getCurrentAuditor();
    assertNull(currentAuditor);
  }

  @Test
  public void testGetCurrentAuditorWithNonAuthenticatedUser() throws Exception {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.isAuthenticated()).thenReturn(false);

    String currentAuditor = springSecurityAuditorAware.getCurrentAuditor();
    assertNull(currentAuditor);
  }

  @Test
  public void testGetCurrentAuditorWithAuthenticatedUser() throws Exception {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.isAuthenticated()).thenReturn(true);
    when(authentication.getName()).thenReturn(CURRENT_AUDITOR);


    String currentAuditor = springSecurityAuditorAware.getCurrentAuditor();
    assertEquals(currentAuditor, CURRENT_AUDITOR);
  }
}