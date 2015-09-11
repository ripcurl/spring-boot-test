package com.kuoni.gts.masterdata.service.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/** Spring-data-jpa auditing */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

  /** current auditor name for lastUpdateBy, createdBy columns */
  public String getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }
    return authentication.getName();
  }
}
