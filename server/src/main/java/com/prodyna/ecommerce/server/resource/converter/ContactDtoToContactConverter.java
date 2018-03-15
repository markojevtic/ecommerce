package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.resource.dto.ContactDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactDtoToContactConverter implements Converter<ContactDto, Contact>{
    @Override
    public Contact convert(ContactDto contactDto) {
        return Contact.builder()
                .email(contactDto.getEmail())
                .name(contactDto.getName())
                .phone(contactDto.getPhone())
                .build();
    }
}
