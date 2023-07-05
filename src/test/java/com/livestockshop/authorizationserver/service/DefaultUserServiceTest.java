package com.livestockshop.authorizationserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.livestockshop.authorizationserver.LivestockShopAuthorizationServerApplication;
import com.livestockshop.authorizationserver.model.entity.UserEntity;
import com.livestockshop.authorizationserver.repository.UserRepository;

@SpringBootTest(classes = LivestockShopAuthorizationServerApplication.class)
@DisplayName("DefaultUserDetailsManager")
@Tag("service")
@Tag("user")
class DefaultUserServiceTest {

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private DefaultUserService userService;

  @Test
  @DisplayName("loadUserByUsername(String) - normal return")
  final void loadUserByUsername_normalReturn() throws Exception {
    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setEmail("email");
    user.setPassword("password");
    user.setAuthorities(new HashSet<>());
    when(this.userRepository.findByEmail(user.getUsername()))
        .thenReturn(Optional.of(user));
    UserEntity foundUser = this.userService.loadUserByUsername(user.getUsername());
    assertEquals(foundUser.getId(), user.getId());
    assertEquals(foundUser.getUsername(), user.getUsername());
    assertEquals(foundUser.getPassword(), user.getPassword());
    assertEquals(foundUser.getAuthorities(), user.getAuthorities());
  }

  @Test
  @DisplayName("loadUserByUsername(String) - user not found")
  final void loadUserByUsername_userNotFound() throws Exception {
    String email = "email";
    when(this.userRepository.findByEmail(email))
        .thenThrow(UsernameNotFoundException.class);
    assertThrows(UsernameNotFoundException.class,
        () -> this.userService.loadUserByUsername(email));
  }
}
