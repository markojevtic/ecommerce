package com.prodyna.ecommerce.server.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto extends ResourceSupport {
    private String name;
    private String email;
    private String phone;
}
