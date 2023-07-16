package com.livestock.authorizationserver.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.livestock.authorizationserver.Application;

@SpringBootTest(classes = Application.class)
@DisplayName("LoginController")
@Tag("controller")
@Tag("login")
@AutoConfigureMockMvc
class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("login() - normal return")
  @WithMockUser
  final void login_normalReturn() throws Exception {
    this.mockMvc.perform(post("/login"))
        .andExpectAll(
            status().isNoContent());
  }
  
  @Test
  @DisplayName("login() - unauthenticated")
  final void login_unauthenticated() throws Exception {
    this.mockMvc.perform(post("/login"))
        .andExpectAll(
            status().isUnauthorized());
  }
}
