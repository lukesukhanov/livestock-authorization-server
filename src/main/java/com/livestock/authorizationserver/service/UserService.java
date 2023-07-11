package com.livestock.authorizationserver.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

import com.livestock.authorizationserver.model.dto.UserForSave;

/**
 * The service for managing users.
 */
public interface UserService extends UserDetailsManager {

  /**
   * Saves a new user.
   * 
   * @param userForSave a {@code UserForSave} with the user's details
   */
  void create(UserForSave userForSave);

  @Override
  default void createUser(UserDetails user) {
    throw new NotImplementedException();
  }
}
