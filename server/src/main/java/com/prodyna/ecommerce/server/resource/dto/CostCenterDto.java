package com.prodyna.ecommerce.server.resource.dto;

import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.repository.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostCenterDto {
    private String costCenterId;
    private String name;
    private Address address;
    private List<Contact> contactList;
}
