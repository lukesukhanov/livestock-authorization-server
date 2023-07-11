package com.livestock.authorizationserver.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.livestock.authorizationserver.LivestockShopAuthorizationServerApplication;
import com.livestock.authorizationserver.model.dto.UserForSave;
import com.livestock.authorizationserver.service.UserService;

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

  @Test
  @DisplayName("create(...) - normal return")
  final void create_normalReturn() throws Exception {
    this.mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("firstName", "Vasya")
        .param("lastName", "Ivanov")
        .param("email", "email@email.com")
        .param("password", "password"))
        .andExpectAll(
            status().isNoContent());
  }

  @Test
  @DisplayName("create(...) - DuplicateKeyException")
  final void create_DuplicateKeyException() throws Exception {
    UserForSave userForSave = new UserForSave("Vasya", "Ivanov", "email@email.com", "password");
    doThrow(new DuplicateKeyException("message"))
        .when(this.userService).create(userForSave);
    this.mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("firstName", userForSave.getFirstName())
        .param("lastName", userForSave.getLastName())
        .param("email", userForSave.getEmail())
        .param("password", userForSave.getPassword()))
        .andExpectAll(
            status().isBadRequest(),
            content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
  }
}
