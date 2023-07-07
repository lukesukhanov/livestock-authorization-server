package com.livestockshop.authorizationserver.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OAuth2TokenSecurityConfig {

  @Value("${app.security.client-urls}")
  private final String[] clientUrls;

  @Bean
  @Order(2)
  SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
      throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
        .oidc(withDefaults());
    return http
        .securityMatcher("/oauth2/token")
        .securityContext(securityContext -> securityContext
            .requireExplicitSave(true))
        .headers(headers -> headers
            .httpStrictTransportSecurity(hsts -> hsts
                .disable()))
        .cors(cors -> cors
            .configurationSource(oAuth2TokenCorsConfigurationSource()))
        .csrf(csrf -> csrf
            .disable())
        .logout(logout -> logout
            .disable())
        .oauth2ResourceServer(resourceServer -> resourceServer
            .jwt(withDefaults())
            .authenticationEntryPoint(oAuth2TokenAuthenticationEntryPoint()))
        .anonymous(anonymous -> anonymous
            .disable())
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.NEVER))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/oauth2/token").authenticated())
        .build();
  }

  @Bean
  CorsConfigurationSource oAuth2TokenCorsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList("*")); // TODO this.clientUrls
    config.setAllowedMethods(List.of(HttpMethod.POST.toString()));
    config.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE));
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/oauth2/token", config);
    return source;
  }

  @Bean
  AuthenticationEntryPoint oAuth2TokenAuthenticationEntryPoint() {
    return (request, response, e) -> {
      response.sendError(
          HttpStatus.UNAUTHORIZED.value(),
          HttpStatus.UNAUTHORIZED.getReasonPhrase());
    };
  }
}