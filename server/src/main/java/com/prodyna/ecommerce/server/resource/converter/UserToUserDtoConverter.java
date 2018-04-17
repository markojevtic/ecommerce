package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.User;
import com.prodyna.ecommerce.server.resource.dto.UserDto;
import org.springframework.core.convert.converter.Converter;


public class UserToUserDtoConverter extends ContextAwareConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .customer(user.getCustomer())
                .active(user.isActive())
                .roles(user.getRoles())
                .build();
    }
}
