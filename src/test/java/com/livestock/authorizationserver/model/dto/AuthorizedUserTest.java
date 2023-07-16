package com.livestock.authorizationserver.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@DisplayName("AuthorizedUser")
@Tag("dto")
@Tag("user")
class AuthorizedUserTest {

  @Test
  @DisplayName("equals(Object) - the same object")
  final void equals_sameObject() throws Exception {
    AuthorizedUser user = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertEquals(user, user);
  }

  @Test
  @DisplayName("equals(Object) - matching object")
  final void equals_matchingObject() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different firstName")
  final void equals_differentFirstName() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name1",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name2",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different lastName")
  final void equals_differentLastName() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name1",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name2",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different email")
  final void equals_differentEmail() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email1",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email2",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different password")
  final void equals_differentPassword() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password1",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password2",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different enabled")
  final void equals_differentEnabled() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        false,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different accountNonExpired")
  final void equals_differentAccountNonExpired() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        false,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different accountNonLocked")
  final void equals_differentAccountNonLocked() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        false,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different credentialsNonExpired")
  final void equals_differentCredentialsNonExpired() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        false,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different authorities")
  final void equals_differentAuthorities() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("hashCode() - matching object")
  final void hashCode_matchingObject() throws Exception {
    AuthorizedUser user1 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    AuthorizedUser user2 = new AuthorizedUser(
        "first name",
        "last name",
        "email",
        "password",
        true,
        true,
        true,
        true,
        Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    assertEquals(user1.hashCode(), user2.hashCode());
  }
}
