package com.livestockshop.authorizationserver.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class LoginSecurityConfig {

  @Bean
  @Order(2)
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    return http
        .securityMatcher("/login")
        .securityContext(securityContext -> securityContext
            .requireExplicitSave(true))
        .headers(headers -> headers
            .httpStrictTransportSecurity(hsts -> hsts
                .disable()))
        .cors(cors -> cors
            .disable())
        .csrf(csrf -> csrf
            .disable())
        .logout(logout -> logout
            .disable())
        .formLogin(withDefaults())
        .anonymous(anonymous -> anonymous
            .disable())
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
            .anyRequest().authenticated())
        .build();
  }
}