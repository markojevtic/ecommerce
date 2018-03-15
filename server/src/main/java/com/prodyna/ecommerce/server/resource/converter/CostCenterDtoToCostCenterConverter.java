package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.resource.dto.AddressDto;
import com.prodyna.ecommerce.server.resource.dto.ContactDto;
import com.prodyna.ecommerce.server.resource.dto.CostCenterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostCenterDtoToCostCenterConverter extends ContextAwareConverter implements Converter<CostCenterDto, CostCenter> {

    @Override
    public CostCenter convert(CostCenterDto costCenterDto) {

        TypeDescriptor typeDscContact = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Contact.class));
        TypeDescriptor typeDscContactDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ContactDto.class));

        return CostCenter.builder()
                .costCenterId(costCenterDto.getCostCenterId())
                .name(costCenterDto.getName())
                .address(conversionService().convert(costCenterDto.getAddress(), Address.class))
                .contactList((List<Contact>) conversionService().convert(costCenterDto.getContactList(), typeDscContactDto, typeDscContact))
                .build();
    }
}


