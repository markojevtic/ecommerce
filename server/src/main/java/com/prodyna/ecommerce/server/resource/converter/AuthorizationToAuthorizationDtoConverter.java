package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Authorization;
import com.prodyna.ecommerce.server.resource.dto.AuthorizationDto;
import org.springframework.core.convert.converter.Converter;

public class AuthorizationToAuthorizationDtoConverter extends ContextAwareConverter implements Converter<Authorization, AuthorizationDto> {
    @Override
    public AuthorizationDto convert(Authorization authorization) {
        return AuthorizationDto.builder()
                .authorizationId(authorization.getAuthorizationId())
                .username(authorization.getUsername())
                .password(authorization.getPassword())
                .build();
    }
}
