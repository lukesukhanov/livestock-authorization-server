package com.livestockshop.authorizationserver.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A user.
 * <p>
 * The {@code equals} method should be used for comparisons.
 * The {@code User} objects are compared by {@code username}, {@code password},
 * {@code enabled} and {@code authorities}.
 * <p>
 * This class is immutable and thread-safe.
 */
@NoArgsConstructor
@EqualsAndHashCode(of = { "username", "password", "enabled", "authorities" })
public final class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "Username is required")
  private String username;

  @NotEmpty(message = "Password is required")
  private String password;

  @NotNull(message = "Property 'enabled' is required")
  private Boolean enabled = true;

  private Set<GrantedAuthority> authorities;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.authorities = Set.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  public User(String username, String password, String... authorities) {
    this.username = username;
    this.password = password;
    Set<GrantedAuthority> tempAuthorities = new HashSet<>(4);
    for (String authority : authorities) {
      tempAuthorities.add(new SimpleGrantedAuthority(authority));
    }
    this.authorities = Collections.unmodifiableSet(tempAuthorities);
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public String toString() {
    return "User [username=%s, password=[PROTECTED], enabled=%b, authorities=%s]".formatted(
        this.username, this.enabled, this.authorities);
  }

}