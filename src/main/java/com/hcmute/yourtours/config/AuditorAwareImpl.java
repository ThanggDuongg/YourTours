package com.hcmute.yourtours.config;

import com.hcmute.yourtours.libs.configuration.security.DefaultUserDetail;
import com.hcmute.yourtours.libs.configuration.security.XAPIAuthentication;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof DefaultUserDetail) {
            DefaultUserDetail defaultUserDetail = (DefaultUserDetail) authentication.getPrincipal();
            return Optional.ofNullable(defaultUserDetail.getSubject() != null ? defaultUserDetail.getSubject() : null);
        }
        if (authentication instanceof XAPIAuthentication) {
//            XAPIAuthentication auth = (XAPIAuthentication) authentication;
            return Optional.ofNullable(authentication.getName());
        }
        return Optional.empty();
    }
}
