package com.livestockshop.authorizationserver.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration(proxyBeanMethods = false)
public class LoginSecurityConfig {

  @Bean
  @Order(3)
  SecurityFilterChain loginGetSecurityFilterChain(HttpSecurity http) throws Exception {
    return http
        .securityMatcher("/login")
        .securityContext(securityContext -> securityContext
            .disable())
        .headers(headers -> headers
            .httpStrictTransportSecurity(hsts -> hsts
                .disable()))
        .cors(cors -> cors
            .configurationSource(loginGetCorsConfigurationSource()))
        .csrf(csrf -> csrf
            .disable())
        .logout(logout -> logout
            .disable())
        .requestCache(requestCache -> requestCache
            .disable())
        .anonymous(anonymous -> anonymous
            .disable())
        .sessionManagement(sessionManagement -> sessionManagement
            .disable())
        .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
            .requestMatchers(HttpMethod.GET, "/login").permitAll()
            .anyRequest().denyAll())
        .build();
  }

  @Bean
  CorsConfigurationSource loginGetCorsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList("*")); // TODO this.clientUrls
    config.setAllowedMethods(List.of(HttpMethod.GET.toString()));
    config.setAllowCredentials(false);
    config.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/login", config);
    return source;
  }

  @Bean
  @Order(4)
  SecurityFilterChain loginPostSecurityFilterChain(HttpSecurity http) throws Exception {
    return http
        .securityMatcher("/login")
        .securityContext(securityContext -> securityContext
            .requireExplicitSave(true))
        .headers(headers -> headers
            .httpStrictTransportSecurity(hsts -> hsts
                .disable()))
        .cors(cors -> cors
            .configurationSource(loginPostCorsConfigurationSource()))
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
            .requestMatchers(HttpMethod.POST, "/login").authenticated())
        .build();
  }

  @Bean
  CorsConfigurationSource loginPostCorsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList("*")); // TODO this.clientUrls
    config.setAllowedMethods(List.of(HttpMethod.POST.toString()));
    config.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE));
    config.setAllowCredentials(true);
    config.setExposedHeaders(List.of(HttpHeaders.LOCATION));
    config.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/login", config);
    return source;
  }
}