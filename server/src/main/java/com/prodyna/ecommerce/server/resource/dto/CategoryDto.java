package com.prodyna.ecommerce.server.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto extends ResourceSupport {
    String categoryId;
    String name;
}