package com.prodyna.ecommerce.server.resource.dto;

import com.prodyna.ecommerce.server.repository.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String name;
    private String description;
    private String photoUrl;
    private Category category;
    private Boolean active;
}
