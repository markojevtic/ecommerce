package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Authorization;
import com.prodyna.ecommerce.server.resource.dto.AuthorizationDto;
import org.springframework.core.convert.converter.Converter;

public class AuthorizationDtoToAuthorizationConverter extends ContextAwareConverter implements Converter<AuthorizationDto, Authorization> {
    @Override
    public Authorization convert(AuthorizationDto authorizationDto) {
        return Authorization.builder()
                .authorizationId(authorizationDto.getAuthorizationId())
                .username(authorizationDto.getUsername())
                .password(authorizationDto.getPassword())
                .build();
    }
}
