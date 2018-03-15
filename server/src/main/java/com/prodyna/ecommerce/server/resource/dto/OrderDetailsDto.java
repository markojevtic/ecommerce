package com.prodyna.ecommerce.server.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto extends ResourceSupport {
    private Double ordinal;
    private ProductDto product;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal amount;
}
