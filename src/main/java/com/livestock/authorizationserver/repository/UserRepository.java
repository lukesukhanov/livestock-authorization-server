package com.livestock.authorizationserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.livestock.authorizationserver.model.entity.UserEntity;

/**
 * Allows accessing persistent information about users.
 * 
 * @see UserEntity
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  /**
   * Finds a user by email.
   * <p>
   * The user is fetched with his authorities.
   * 
   * @param email a {@code String} with the user's email
   * @return a {@code Optional} with the found {@code UserEntity}
   */
  @Query(name = UserEntity.JPQL_FIND_USER_BY_EMAIL)
  Optional<UserEntity> findByEmail(@Param("email") String email);
}
