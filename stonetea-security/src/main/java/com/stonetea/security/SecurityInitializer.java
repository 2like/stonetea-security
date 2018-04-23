package com.stonetea.security;

import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {


        public SecurityInitializer() {
                super(SecurityConfig.class, EmbeddedRedisConfiguration.class);
        }
}