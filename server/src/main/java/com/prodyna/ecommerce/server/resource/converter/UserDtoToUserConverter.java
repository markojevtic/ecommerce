package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.User;
import com.prodyna.ecommerce.server.resource.dto.UserDto;
import org.springframework.core.convert.converter.Converter;

public class UserDtoToUserConverter extends ContextAwareConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .fullName(userDto.getFullName())
                .customer(userDto.getCustomer())
                .active(userDto.isActive())
                .roles(userDto.getRoles())
                .build();
    }
}
