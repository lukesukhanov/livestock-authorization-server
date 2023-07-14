package com.livestock.authorizationserver.config;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class SecurityConfig {

  @Value("${app.security.cors-origins}")
  private String[] corsOrigins;

  @Bean
  @Order(1)
  SecurityFilterChain loginSecurityFilterChain(HttpSecurity http) throws Exception {
    return http
        .securityMatcher("/login")
        .securityContext(securityContext -> securityContext
            .requireExplicitSave(true))
        .headers(headers -> headers
            .httpStrictTransportSecurity(hsts -> hsts
                .disable()))
        .cors(cors -> cors
            .configurationSource(loginCorsConfigurationSource()))
        .csrf(csrf -> csrf
            .disable())
        .logout(logout -> logout
            .disable())
        .httpBasic(httpBasic -> httpBasic
            .authenticationEntryPoint(defaultAuthenticationEntryPoint()))
        .anonymous(anonymous -> anonymous
            .disable())
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
            .requestMatchers(HttpMethod.POST, "/login").authenticated())
        .build();
  }

  @Bean
  CorsConfigurationSource loginCorsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList(this.corsOrigins));
    config.setAllowedMethods(List.of(HttpMethod.POST.toString()));
    config.setAllowedHeaders(List.of(HttpHeaders.AUTHORIZATION));
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/login", config);
    return source;
  }

  @Bean
  @Order(2)
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
        .anonymous(anonymous -> anonymous
            .disable())
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint(defaultAuthenticationEntryPoint()))
        .build();
  }

  @Bean
  CorsConfigurationSource oauth2AuthorizeCorsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList(this.corsOrigins));
    config.setAllowedMethods(List.of(HttpMethod.GET.toString()));
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/oauth2/authorize", config);
    return source;
  }

  @Bean
  @Order(3)
  SecurityFilterChain oauth2TokenSecurityFilterChain(HttpSecurity http)
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
            .configurationSource(oauth2TokenCorsConfigurationSource()))
        .csrf(csrf -> csrf
            .disable())
        .logout(logout -> logout
            .disable())
        .anonymous(anonymous -> anonymous
            .disable())
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.NEVER))
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint(defaultAuthenticationEntryPoint()))
        .build();
  }

  @Bean
  CorsConfigurationSource oauth2TokenCorsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList(this.corsOrigins));
    config.setAllowedMethods(List.of(HttpMethod.POST.toString()));
    config.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE));
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/oauth2/token", config);
    return source;
  }

  @Bean
  @Order(4)
  SecurityFilterChain usersSecurityFilterChain(HttpSecurity http) throws Exception {
    return http
        .securityMatcher("/users")
        .securityContext(securityContext -> securityContext
            .disable())
        .headers(headers -> headers
            .httpStrictTransportSecurity(hsts -> hsts
                .disable()))
        .cors(cors -> cors
            .configurationSource(usersCorsConfigurationSource()))
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
            .requestMatchers(HttpMethod.POST, "/users").permitAll())
        .build();
  }

  @Bean
  CorsConfigurationSource usersCorsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList(this.corsOrigins));
    config.setAllowedMethods(List.of(HttpMethod.POST.toString()));
    config.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
    config.setAllowCredentials(false);
    config.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/users", config);
    return source;
  }

  @Bean
  @Order(5)
  SecurityFilterChain endpointsForClientsSecurityFilterChain(HttpSecurity http)
      throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
        .oidc(withDefaults());
    return http
        .securityMatcher("/.well-known/*", "/oauth2/jwks")
        .securityContext(securityContext -> securityContext
            .disable())
        .headers(headers -> headers
            .httpStrictTransportSecurity(hsts -> hsts
                .disable()))
        .cors(cors -> cors
            .disable())
        .csrf(csrf -> csrf
            .disable())
        .logout(logout -> logout
            .disable())
        .anonymous(anonymous -> anonymous
            .disable())
        .sessionManagement(sessionManagement -> sessionManagement
            .disable())
        .build();
  }

  @Bean
  AuthenticationEntryPoint defaultAuthenticationEntryPoint() {
    return (request, response, e) -> {
      response.sendError(
          HttpStatus.UNAUTHORIZED.value(),
          HttpStatus.UNAUTHORIZED.getReasonPhrase());
    };
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }
}