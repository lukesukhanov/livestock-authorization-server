package com.livestockshop.authorizationserver.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@DisplayName("User")
@Tag("dto")
@Tag("user")
class AuthorizedUserTest {

  @Test
  @DisplayName("equals(Object) - the same object")
  final void equals_sameObject() throws Exception {
    AuthorizedUser user = new AuthorizedUser("email", "password", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertEquals(user, user);
  }

  @Test
  @DisplayName("equals(Object) - matching object")
  final void equals_matchingObject() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser("email", "password", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser("email", "password", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different email")
  final void equals_differentEmail() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser("email1", "password", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser("email2", "password", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different password")
  final void equals_differentPassword() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser("email", "password1", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser("email", "password2", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different authorities")
  final void equals_differentAuthorities() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser("email", "password", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser("email", "password", Set.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("hashCode() - matching object")
  final void hashCode_matchingObject() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser("email", "password", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser("email", "password", Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertEquals(user1.hashCode(), user2.hashCode());
  }
}
