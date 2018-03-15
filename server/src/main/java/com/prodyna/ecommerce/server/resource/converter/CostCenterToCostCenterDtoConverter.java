package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.resource.dto.AddressDto;
import com.prodyna.ecommerce.server.resource.dto.ContactDto;
import com.prodyna.ecommerce.server.resource.dto.CostCenterDto;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostCenterToCostCenterDtoConverter extends ContextAwareConverter implements Converter<CostCenter, CostCenterDto> {

    TypeDescriptor typeDscContact = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Contact.class));
    TypeDescriptor typeDscContactDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ContactDto.class));

    @Override
    public CostCenterDto convert(CostCenter costCenter) {
        return CostCenterDto.builder()
                .costCenterId(costCenter.getCostCenterId())
                .name(costCenter.getName())
                .address(conversionService().convert(costCenter.getAddress(), AddressDto.class))
                .contactList((List<ContactDto>) conversionService().convert(costCenter.getContactList(), typeDscContact, typeDscContactDto))
                .build();
    }
}
