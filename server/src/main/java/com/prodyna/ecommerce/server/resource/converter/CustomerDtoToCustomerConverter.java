package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.resource.dto.AddressDto;
import com.prodyna.ecommerce.server.resource.dto.ContactDto;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDtoToCustomerConverter extends ContextAwareConverter implements Converter<CustomerDto, Customer> {

    TypeDescriptor typeDscContact = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Contact.class));
    TypeDescriptor typeDscContactDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ContactDto.class));

    @Override
    public Customer convert(CustomerDto customer) {
        return Customer.builder()
                .customerId(customer.getCustomerId())
                .active(customer.getActive())
                .address(conversionService().convert(customer.getAddress(), Address.class))
                .billingAddresses(conversionService().convert(customer.getBillingAddresses(), Address.class))
                .costCenter(conversionService().convert(customer.getCostCenter(), CostCenter.class))
                .name(customer.getName())
                .contacts((List<Contact>) conversionService().convert(customer.getContacts(), typeDscContactDto, typeDscContact))
                .build();
    }
}
