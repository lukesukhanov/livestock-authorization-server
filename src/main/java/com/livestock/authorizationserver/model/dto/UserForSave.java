package com.livestock.authorizationserver.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A user.
 * <p>
 * The {@code equals} method should be used for comparisons.
 * The {@code UserEntity} objects are compared by {@code firstName},
 * {@code lastName}, {@code username} and {@code password}.
 * <p>
 * This class is immutable and thread-safe.
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class UserForSave implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "First name is required")
  private final String firstName;

  @NotBlank(message = "Last name is required")
  private final String lastName;

  @Email(message = "Email is incorrect")
  @NotNull(message = "Email is required")
  private final String email;

  @NotNull(message = "Password is required")
  private final String password;
}
