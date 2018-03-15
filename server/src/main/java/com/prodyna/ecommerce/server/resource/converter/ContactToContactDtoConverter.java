package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.resource.dto.ContactDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactToContactDtoConverter implements Converter<Contact, ContactDto> {
    @Override
    public ContactDto convert(Contact contact) {
        return ContactDto.builder()
                .email(contact.getEmail())
                .name(contact.getName())
                .phone(contact.getPhone())
                .build();
    }
}
