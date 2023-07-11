package com.livestock.authorizationserver.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("UserEntity")
@Tag("entity")
@Tag("user")
class UserEntityTest {

  @Test
  @DisplayName("equals(Object) - the same object")
  final void equals_sameObject() throws Exception {
    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setFirstName("first name");
    user.setLastName("last name");
    user.setEmail("email");
    user.setPassword("password");
    user.setAuthorities(new HashSet<>());
    assertEquals(user, user);
  }

  @Test
  @DisplayName("equals(Object) - the same id")
  final void equals_sameId() throws Exception {
    UserEntity user1 = new UserEntity();
    user1.setId(1L);
    user1.setFirstName("first name1");
    user1.setLastName("last name1");
    user1.setEmail("email1");
    user1.setPassword("password1");
    user1.setAuthorities(new HashSet<>());
    UserEntity user2 = new UserEntity();
    user2.setId(1L);
    user2.setFirstName("first name2");
    user2.setLastName("last name2");
    user2.setEmail("email2");
    user2.setPassword("password2");
    user2.setAuthorities(new HashSet<>());
    assertEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different id")
  final void equals_differentId() throws Exception {
    UserEntity user1 = new UserEntity();
    user1.setId(1L);
    user1.setFirstName("first name");
    user1.setLastName("last name");
    user1.setEmail("email");
    user1.setPassword("password");
    user1.setAuthorities(new HashSet<>());
    UserEntity user2 = new UserEntity();
    user2.setId(2L);
    user1.setFirstName("first name");
    user1.setLastName("last name");
    user2.setEmail("email");
    user2.setPassword("password");
    user2.setAuthorities(new HashSet<>());
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - one of the ids is null")
  final void equals_oneOfIdsIsNull() throws Exception {
    UserEntity user1 = new UserEntity();
    user1.setId(1L);
    user1.setFirstName("first name");
    user1.setLastName("last name");
    user1.setEmail("email");
    user1.setPassword("password");
    user1.setAuthorities(new HashSet<>());
    UserEntity user2 = new UserEntity();
    user2.setId(null);
    user1.setFirstName("first name");
    user1.setLastName("last name");
    user2.setEmail("email");
    user2.setPassword("password");
    user2.setAuthorities(new HashSet<>());
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - both ids are null")
  final void equals_bothIdsAreNull() throws Exception {
    UserEntity user1 = new UserEntity();
    user1.setId(null);
    user1.setFirstName("first name");
    user1.setLastName("last name");
    user1.setEmail("email");
    user1.setPassword("password");
    user1.setAuthorities(new HashSet<>());
    UserEntity user2 = new UserEntity();
    user2.setId(null);
    user1.setFirstName("first name");
    user1.setLastName("last name");
    user2.setEmail("email");
    user2.setPassword("password");
    user2.setAuthorities(new HashSet<>());
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("hashCode() - any other UserEntity")
  final void hashCode_anyOtherUserEntity() throws Exception {
    UserEntity user1 = new UserEntity();
    user1.setId(1L);
    user1.setFirstName("first name1");
    user1.setLastName("last name1");
    user1.setEmail("email1");
    user1.setPassword("password1");
    user1.setAuthorities(new HashSet<>());
    UserEntity user2 = new UserEntity();
    user2.setId(2L);
    user1.setFirstName("first name2");
    user1.setLastName("last name2");
    user2.setEmail("email2");
    user2.setPassword("password2");
    user2.setAuthorities(new HashSet<>());
    assertEquals(user1.hashCode(), user2.hashCode());
  }
}
