package com.livestockshop.authorizationserver.service;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.livestockshop.authorizationserver.model.entity.UserEntity;
import com.livestockshop.authorizationserver.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * The default {@code UserService} implementation.
 */
@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  @Override
  public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.userRepository
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(
            "Can't find a user with email '%s'".formatted(email)));
  }

  @Override
  public Long save(String email, String password) {
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(email);
    userEntity.setPassword(this.passwordEncoder.encode(password));
    userEntity.setAuthorities(Set.of(
        new SimpleGrantedAuthority("ROLE_USER"),
        new SimpleGrantedAuthority("openid"),
        new SimpleGrantedAuthority("profile")));
    userEntity = this.userRepository.save(userEntity);
    return userEntity.getId();
  }

  @Override
  public void updateUser(UserDetails user) {
  }

  @Override
  public void deleteUser(String username) {
  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {
  }

  @Override
  public boolean userExists(String username) {
    return false;
  }
}
