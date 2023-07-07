package com.livestockshop.authorizationserver.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OAuth2AuthorizeSecurityConfig {

  @Value("${app.security.client-urls}")
  private final String[] clientUrls;

  @Bean
  @Order(1)
  SecurityFilterChain oauth2AuthorizeSecurityFilterChain(HttpSecurity http)
      throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
        .oidc(withDefaults());
    return http
        .securityMatcher("/oauth2/authorize")
        .securityContext(securityContext -> securityContext
            .requireExplicitSave(true))
        .headers(headers -> headers
            .httpStrictTransportSecurity(hsts -> hsts
                .disable()))
        .cors(cors -> cors
            .configurationSource(oauth2AuthorizeCorsConfigurationSource()))
        .csrf(csrf -> csrf
            .disable())
        .logout(logout -> logout
            .disable())
        .oauth2ResourceServer(resourceServer -> resourceServer
            .jwt(withDefaults())
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
        .anonymous(anonymous -> anonymous
            .disable())
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .build();
  }

  @Bean
  CorsConfigurationSource oauth2AuthorizeCorsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList("*")); // TODO this.clientUrls
    config.setAllowedMethods(List.of(HttpMethod.GET.toString()));
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/oauth2/authorize", config);
    return source;
  }
}