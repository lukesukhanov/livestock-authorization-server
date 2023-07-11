package com.livestock.authorizationserver.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("UserForSave")
@Tag("dto")
@Tag("user")
class UserForSaveTest {

  @Test
  @DisplayName("equals(Object) - the same object")
  final void equals_sameObject() throws Exception {
    UserForSave user = new UserForSave(
        "first name",
        "last name",
        "email",
        "password");
    assertEquals(user, user);
  }

  @Test
  @DisplayName("equals(Object) - matching object")
  final void equals_matchingObject() throws Exception {
    UserForSave user1 = new UserForSave(
        "first name",
        "last name",
        "email",
        "password");
    UserForSave user2 = new UserForSave(
        "first name",
        "last name",
        "email",
        "password");
    assertEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different firstName")
  final void equals_differentFirstName() throws Exception {
    UserForSave user1 = new UserForSave(
        "first name1",
        "last name",
        "email",
        "password");
    UserForSave user2 = new UserForSave(
        "first name2",
        "last name",
        "email",
        "password");
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different lastName")
  final void equals_differentLastName() throws Exception {
    UserForSave user1 = new UserForSave(
        "first name",
        "last name1",
        "email",
        "password");
    UserForSave user2 = new UserForSave(
        "first name",
        "last name2",
        "email",
        "password");
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different email")
  final void equals_differentEmail() throws Exception {
    UserForSave user1 = new UserForSave(
        "first name",
        "last name",
        "email1",
        "password");
    UserForSave user2 = new UserForSave(
        "first name",
        "last name",
        "email2",
        "password");
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("equals(Object) - different password")
  final void equals_differentPassword() throws Exception {
    UserForSave user1 = new UserForSave(
        "first name",
        "last name",
        "email",
        "password1");
    UserForSave user2 = new UserForSave(
        "first name",
        "last name",
        "email",
        "password2");
    assertNotEquals(user1, user2);
  }

  @Test
  @DisplayName("hashCode() - matching object")
  final void hashCode_matchingObject() throws Exception {
    UserForSave user1 = new UserForSave(
        "first name",
        "last name",
        "email",
        "password");
    UserForSave user2 = new UserForSave(
        "first name",
        "last name",
        "email",
        "password");
    assertEquals(user1.hashCode(), user2.hashCode());
  }
}
