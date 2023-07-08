package com.livestockshop.authorizationserver.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import com.livestockshop.authorizationserver.model.dto.AuthorizedUser;
import com.livestockshop.authorizationserver.model.entity.UserEntity;

/**
 * The mappings between objects associated with users.
 */
@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  @Mapping(target = "username", source = "email")
  AuthorizedUser userEntityToAuthorizedUser(UserEntity userEntity);
}
