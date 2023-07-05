package com.livestockshop.authorizationserver.model.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.livestockshop.authorizationserver.repository.UserRepository;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A user entity.
 * <p>
 * The {@code equals} method should be used for comparisons.
 * The {@code UserEntity} objects are compared by {@code id}.
 * The {@code UserEntity} with {@code id = null} is equal only to itself.
 * <p>
 * The {@code hashCode} method always returns the same value.
 * <p>
 * This class is not immutable and is not supposed to be used concurrently.
 * 
 * @see UserRepository
 */
@Entity
@Table(name = "USERS")
@DynamicUpdate
@NamedQuery(
    name = "find_user_by_email",
    query = "select u from UserEntity u join fetch u.authorities where u.email = :email")
@NoArgsConstructor
@Setter
public final class UserEntity implements UserDetails {

  public static final String JPQL_FIND_USER_BY_EMAIL = "find_user_by_email";

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "common_id_seq")
  @SequenceGenerator(name = "common_id_seq", sequenceName = "COMMON_ID_SEQ", allocationSize = 5)
  @Column(name = "ID", updatable = false)
  @Getter
  private Long id;

  @Email(message = "Email is incorrect")
  @NotBlank(message = "Email is required")
  @Column(name = "EMAIL")
  private String email;

  @NotEmpty(message = "Password is required")
  @Column(name = "PASSWORD")
  private String password;

  @ElementCollection
  @CollectionTable(name = "AUTHORITIES", joinColumns = @JoinColumn(name = "USERS_ID"))
  @Column(name = "AUTHORITY")
  private Set<GrantedAuthority> authorities = new HashSet<>(3);

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserEntity)) {
      return false;
    }
    UserEntity other = (UserEntity) o;
    return this.id != null && this.id.equals(other.getId());
  }
}