package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.resource.dto.AddressDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressDtoConverter implements Converter<Address, AddressDto>{
    @Override
    public AddressDto convert(Address address) {
        return AddressDto.builder()
                .city(address.getCity())
                .zip(address.getZip())
                .streetAndNumber(address.getStreetAndNumber())
                .build();
    }
}
