package com.livestock.authorizationserver.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livestock.authorizationserver.exception.GeneralResponseEntityExceptionHandler;
import com.livestock.authorizationserver.model.dto.UserForSave;
import com.livestock.authorizationserver.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Provides the endpoints for accessing users.
 * <p>
 * The endpoints {@code /users/**} are used.
 * <p>
 * The JSON format is used for the response body.
 * 
 * @see UserService
 * @see GeneralResponseEntityExceptionHandler
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /**
   * Saves a new user.
   * <p>
   * Serves the {@code POST} requests for the {@code /users} endpoint.
   * <p>
   * Query parameters:
   * <ul>
   * <li>firstName - the user's first name, type: string, required</li>
   * <li>lastName - the user's last name, type: string, required</li>
   * <li>email - the user's email, type: string, required</li>
   * <li>password - the user's password, type: string, required</li>
   * </ul>
   * <p>
   * <b>Usage example</b>
   * <p>
   * <i>Request</i>
   * <p>
   * POST /users<br />
   * Content-Type: application/x-www-form-urlencoded<br />
   * Accept: application/json<br />
   * <br />
   * firstName=Vasya&lastName=Ivanov&email=vasya@gmail.com&password=vasya123
   * <p>
   * <i>Normal response</i>
   * <p>
   * Status: 204<br />
   * <p>
   * <i>Response in case of invalid request parameters</i>
   * <p>
   * Status: 400<br />
   * Body: {type: "/probs/invalidRequestParameters", title: "Invalid request
   * parameters", status: 400, invalid-params: [{"name": "email", "reason":
   * "Email is incorrect"}]}
   * <p>
   * <i>Response in case of data integrity violation</i>
   * <p>
   * Status: 400<br />
   * Body: {type: "/probs/dataIntegrityViolation", title: "could not execute
   * batch ...", status: 400}
   * 
   * @param userForSave a {@code UserForSave} with the user's details
   * @return a {@code ResponseEntity} with the status {@code 204}
   */
  @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> create(@Valid UserForSave userForSave) {
    this.userService.create(userForSave);
    return ResponseEntity.noContent().build();
  }
}
