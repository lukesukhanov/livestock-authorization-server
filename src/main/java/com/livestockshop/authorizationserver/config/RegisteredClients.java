package com.livestockshop.authorizationserver.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import lombok.extern.slf4j.Slf4j;

@Configuration(proxyBeanMethods = false)
@Slf4j
public class RegisteredClients {

  @Bean
  CommandLineRunner registerClients(RegisteredClientRepository registeredClientRepository,
      PasswordEncoder passwordEncoder) {
    return args -> {
      RegisteredClient productServiceClient = RegisteredClient
          .withId("product-service")
          .clientId("product-service")
          .clientName("product-service")
          .clientSecret(passwordEncoder.encode("product-service-secret"))
          .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
          .clientSettings(ClientSettings.builder()
              .requireAuthorizationConsent(true)
              .requireProofKey(true)
              .build())
          .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
          .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
          .redirectUri("http://127.0.0.1:8081")
          .postLogoutRedirectUri("http://127.0.0.1:8081")
          .scope(OidcScopes.OPENID)
          .scope(OidcScopes.PROFILE)
          .tokenSettings(TokenSettings.builder()
              .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
              .accessTokenTimeToLive(Duration.of(5, ChronoUnit.MINUTES))
              .refreshTokenTimeToLive(Duration.of(30, ChronoUnit.MINUTES))
              .reuseRefreshTokens(false)
              .authorizationCodeTimeToLive(Duration.of(30, ChronoUnit.SECONDS))
              .build())
          .build();
      registeredClientRepository.save(productServiceClient);
      log.info("Registered product-service client: {}", productServiceClient);
    };
  }
}