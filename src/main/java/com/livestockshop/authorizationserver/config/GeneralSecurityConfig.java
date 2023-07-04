package com.livestockshop.authorizationserver.config;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GeneralSecurityConfig {

  @Bean
  UserDetailsManager defaultUserDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner registerUsers(UserDetailsManager userDetailsManager,
      PasswordEncoder passwordEncoder) {
    return args -> {
      User admin = new User("admin", passwordEncoder.encode("admin"),
          "ROLE_ADMIN", "ROLE_USER");
      userDetailsManager.createUser(admin);
      log.info("Created the user with admin authorities: {}", admin);
    };
  }
}
