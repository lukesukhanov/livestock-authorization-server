package com.livestock.authorizationserver.service;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.livestock.authorizationserver.model.dto.AuthorizedUser;
import com.livestock.authorizationserver.model.dto.UserForSave;
import com.livestock.authorizationserver.model.entity.UserEntity;
import com.livestock.authorizationserver.model.mapper.UserMapper;
import com.livestock.authorizationserver.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * The default {@code UserService} implementation.
 */
@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  @Transactional(readOnly = true)
  @Override
  public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = this.userRepository
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(
            "Can't find a user with email '%s'".formatted(email)));
    return this.userMapper.userEntityToAuthorizedUser(userEntity);
  }

  @Override
  public void create(UserForSave userForSave) {
    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(userForSave.getFirstName());
    userEntity.setLastName(userForSave.getLastName());
    userEntity.setEmail(userForSave.getEmail());
    userEntity.setPassword(this.passwordEncoder.encode(userForSave.getPassword()));
    userEntity.setAuthorities(Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    userEntity.setEnabled(true);
    userEntity.setAccountNonExpired(true);
    userEntity.setAccountNonLocked(true);
    userEntity.setCredentialsNonExpired(true);
    userEntity = this.userRepository.save(userEntity);
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
