package com.prodyna.ecommerce.server.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    private Double ordinal;
    private Product product;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal amount;
}
