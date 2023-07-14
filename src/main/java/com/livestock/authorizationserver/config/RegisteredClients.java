package com.livestock.authorizationserver.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
  CommandLineRunner registerClients(RegisteredClientRepository registeredClientRepository) {
    return args -> {
      RegisteredClient livestockWebClient = RegisteredClient
          .withId("livestock-web")
          .clientId("livestock-web")
          .clientName("livestock-web")
          .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
          .clientSettings(ClientSettings.builder()
              .requireAuthorizationConsent(false)
              .requireProofKey(true)
              .build())
          .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
          .redirectUri("http://localhost:5500")
          .postLogoutRedirectUri("http://localhost:5500")
          .scope(OidcScopes.OPENID)
          .scope("cart")
          .scope("cart.write")
          .tokenSettings(TokenSettings.builder()
              .authorizationCodeTimeToLive(Duration.of(10, ChronoUnit.SECONDS))
              .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
              .accessTokenTimeToLive(Duration.of(10, ChronoUnit.MINUTES))
              .build())
          .build();
      registeredClientRepository.save(livestockWebClient);
      log.info("Registered livestock-web client: {}", livestockWebClient);
    };
  }
}
