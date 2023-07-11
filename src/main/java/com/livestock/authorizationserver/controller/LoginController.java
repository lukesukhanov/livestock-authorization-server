package com.livestock.authorizationserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Provides the endpoint for logging in.
 * <p>
 * The endpoint {@code /login} is used.
 */
@RestController
@RequestMapping(path = "/login")
@RequiredArgsConstructor
public class LoginController {

  /**
   * Logs in a user.
   * <p>
   * Serves the {@code POST} requests for the {@code /login} endpoint.
   * <p>
   * <b>Usage example</b>
   * <p>
   * <i>Request</i>
   * <p>
   * POST /login<br />
   * Authorization: vasya:vasya123
   * <p>
   * or
   * <p>
   * POST /login<br />
   * Cookie: JSESSIONID=...
   * <p>
   * <i>Normal response</i>
   * <p>
   * Status: 200<br />
   * <p>
   * <i>Response in case of failed authentication</i>
   * <p>
   * Status: 401<br />
   * 
   * @return a {@code ResponseEntity} with the status {@code 200}
   */
  @PostMapping
  public ResponseEntity<?> login() {
    return ResponseEntity.ok().build();
  }
}
