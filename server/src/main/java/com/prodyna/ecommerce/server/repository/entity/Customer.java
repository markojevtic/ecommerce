package com.prodyna.ecommerce.server.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private String customerId;
    private String name;
    private List<Contact> contacts;
    private Address address;
    private Address billingAddresses;
    private CostCenter costCenter;
    private Boolean active;
}
