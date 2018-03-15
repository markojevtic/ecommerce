package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.resource.dto.AddressDto;
import com.prodyna.ecommerce.server.resource.dto.ContactDto;
import com.prodyna.ecommerce.server.resource.dto.CostCenterDto;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerToCustomerDtoConverter extends ContextAwareConverter implements Converter<Customer, CustomerDto>{

    TypeDescriptor typeDscContact = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Contact.class));
    TypeDescriptor typeDscContactDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ContactDto.class));

    @Override
    public CustomerDto convert(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .active(customer.getActive())
                .address(conversionService().convert(customer.getAddress(), AddressDto.class))
                .billingAddresses(conversionService().convert(customer.getBillingAddresses(), AddressDto.class))
                .costCenter(conversionService().convert(customer.getCostCenter(), CostCenterDto.class))
                .name(customer.getName())
                .contacts((List<ContactDto>) conversionService().convert(customer.getContacts(), typeDscContact, typeDscContactDto))
                .build();
    }
}
