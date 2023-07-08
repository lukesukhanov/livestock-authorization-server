package com.livestockshop.authorizationserver.model.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

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
    query = "select u from UserEntity u "
        + "join fetch u.authorities "
        + "where u.email = :email")
@NoArgsConstructor
@Getter
@Setter
public final class UserEntity {

  public static final String JPQL_FIND_USER_BY_EMAIL = "find_user_by_email";

  @Id
  @GeneratedValue(generator = "common_id_seq")
  @SequenceGenerator(name = "common_id_seq", sequenceName = "COMMON_ID_SEQ", allocationSize = 5)
  @Column(name = "ID", updatable = false)
  private Long id;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "ENABLED")
  private Boolean enabled;

  @Column(name = "ACCOUNT_NON_EXPIRED")
  private Boolean accountNonExpired;

  @Column(name = "ACCOUNT_NON_LOCKED")
  private Boolean accountNonLocked;

  @Column(name = "CREDENTIALS_NON_EXPIRED")
  private Boolean credentialsNonExpired;

  @ElementCollection
  @CollectionTable(name = "AUTHORITIES", joinColumns = @JoinColumn(name = "USERS_ID"))
  @Column(name = "AUTHORITY")
  private Set<GrantedAuthority> authorities = new HashSet<>(3);

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