package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDtoConverter implements Converter<Customer, CustomerDto>{
    @Override
    public CustomerDto convert(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .active(customer.isActive())
                .address(customer.getAddress())
                .billingAddresses(customer.getBillingAddresses())
                .costCenter(customer.getCostCenter())
                .name(customer.getName())
                .contacts(customer.getContacts())
                .build();
    }
}
