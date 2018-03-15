package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.resource.dto.AddressDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddressConverter implements Converter<AddressDto, Address>{
    @Override
    public Address convert(AddressDto addressDto) {
        return Address.builder()
                .city(addressDto.getCity())
                .zip(addressDto.getZip())
                .streetAndNumber(addressDto.getStreetAndNumber())
                .build();
    }
}
