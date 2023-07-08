package com.livestockshop.authorizationserver.model.dto;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A user.
 * <p>
 * The {@code equals} method should be used for comparisons.
 * The {@code UserEntity} objects are compared by {@code username},
 * {@code password} and {@code authorities}.
 * <p>
 * This class is not immutable and is not supposed to be used concurrently.
 */
@JsonDeserialize
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AuthorizedUser implements UserDetails {

  private static final long serialVersionUID = 1L;

  private String username;

  private String password;

  private boolean enabled;

  private boolean accountNonExpired;

  private boolean accountNonLocked;

  private boolean credentialsNonExpired;

  private Set<GrantedAuthority> authorities;

  public AuthorizedUser(String username, String password, Set<GrantedAuthority> authorities) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }
}
