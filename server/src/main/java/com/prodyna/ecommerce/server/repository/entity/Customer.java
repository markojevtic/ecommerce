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
public class Customer {
    @Id
    private String customerId;
    private String name;
    private List<Contact> contacts;
    private Address address;
    /*Mozda address ili billingAddresses moze da bude indeksirano?
    Deluje mi kao logican juzkejs da neko kaze:"Daj mi sve porudzbine iz Beograda" ili "Daj mi sve porudzbine koje se isporucuju u Beograd"*/
    private Address billingAddresses;
    private CostCenter costCenter;
    private Boolean active;
}
