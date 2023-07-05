package com.livestockshop.authorizationserver.model.converter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GrantedAuthorityConverter implements AttributeConverter<GrantedAuthority, String> {

  @Override
  public String convertToDatabaseColumn(GrantedAuthority grantedAuthority) {
    return grantedAuthority.getAuthority();
  }

  @Override
  public GrantedAuthority convertToEntityAttribute(String grantedAuthority) {
    return new SimpleGrantedAuthority(grantedAuthority);
  }
}
