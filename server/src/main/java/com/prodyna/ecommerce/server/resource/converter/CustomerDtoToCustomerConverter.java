package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(CustomerDto customer) {
        return Customer.builder()
                .customerId(customer.getCustomerId())
                .active(customer.getActive())
                .address(customer.getAddress())
                .billingAddresses(customer.getBillingAddresses())
                .costCenter(customer.getCostCenter())
                .name(customer.getName())
                .contacts(customer.getContacts())
                .build();
    }
}
