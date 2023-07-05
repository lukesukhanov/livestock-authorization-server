package com.livestockshop.authorizationserver.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livestockshop.authorizationserver.LivestockShopAuthorizationServerApplication;
import com.livestockshop.authorizationserver.service.UserService;

@SpringBootTest(classes = LivestockShopAuthorizationServerApplication.class)
@DisplayName("UserController")
@Tag("controller")
@Tag("user")
@AutoConfigureMockMvc
class UserControllerTest {

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("create(...) - normal return")
  final void create_normalReturn() throws Exception {
    String email = "email";
    String password = "password";
    Long idOfSavedUser = 1L;
    Map<String, ?> responseBody = Map.of("userId", idOfSavedUser);
    when(this.userService.save(email, password))
        .thenReturn(idOfSavedUser);
    this.mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("email", email)
        .param("password", password))
        .andExpectAll(
            status().isOk(),
            content().contentType(MediaType.APPLICATION_JSON),
            content().bytes(this.objectMapper.writeValueAsBytes(responseBody)));
  }

  @Test
  @DisplayName("create(...) - DuplicateKeyException")
  final void create_DuplicateKeyException() throws Exception {
    String email = "email";
    String password = "password";
    DuplicateKeyException e = new DuplicateKeyException("message");
    when(this.userService.save(email, password))
        .thenThrow(e);
    this.mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("email", email)
        .param("password", password))
        .andExpectAll(
            status().isBadRequest(),
            content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
  }
}
