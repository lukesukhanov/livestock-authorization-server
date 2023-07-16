package com.livestock.authorizationserver.model.dto;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A user.
 * <p>
 * The {@code equals} method should be used for comparisons.
 * The {@code UserEntity} objects are compared by {@code firstName},
 * {@code lastName}, {@code username}, {@code password}, {@code enabled},
 * {@code accountNonExpired}, {@code accountNonLocked},
 * {@code credentialsNonExpired} and {@code authorities}.
 * <p>
 * This class is not immutable and is not supposed to be used concurrently.
 */
@JsonDeserialize
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AuthorizedUser implements UserDetails {

  private static final long serialVersionUID = 1L;

  private String firstName;

  private String lastName;

  /* Email */
  private String username;

  private String password;

  private boolean enabled;

  private boolean accountNonExpired;

  private boolean accountNonLocked;

  private boolean credentialsNonExpired;

  private Set<GrantedAuthority> authorities;
}
