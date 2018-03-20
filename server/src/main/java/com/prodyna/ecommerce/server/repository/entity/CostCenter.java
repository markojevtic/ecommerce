package com.prodyna.ecommerce.server.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostCenter {
    @Id
    private String costCenterId;
    private String name;
    private Address address;
    private List<Contact> contactList;
}
