package com.prodyna.ecommerce.server.resource.dto;

import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto extends ResourceSupport {
    private String customerId;
    private String name;
    private List<ContactDto> contacts;
    private AddressDto address;
    private AddressDto billingAddresses;
    private CostCenterDto costCenter;
    private Boolean active;
}
