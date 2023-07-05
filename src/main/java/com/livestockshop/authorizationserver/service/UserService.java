package com.livestockshop.authorizationserver.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * The service for managing users.
 */
public interface UserService extends UserDetailsManager {

  /**
   * Saves a new user.
   * 
   * @param email a {@code String} with the user's email
   * @param password a {@code String} with the user's password
   * @return a {@code Long} representing an id of the saved user
   */
  Long save(String email, String password);

  @Override
  default void createUser(UserDetails user) {
    throw new NotImplementedException();
  }
}
