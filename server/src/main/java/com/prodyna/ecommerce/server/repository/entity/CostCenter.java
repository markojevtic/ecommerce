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
public class CostCenter {
    private String costCenterId;
    private String name;
    private Address address;
    private List<Contact> contactList;
}
