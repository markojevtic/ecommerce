package com.prodyna.ecommerce.server.resource.dto;

import com.prodyna.ecommerce.server.repository.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends ResourceSupport {
    private String orderId;
    private Date orderDate;
    private CustomerDto customer;
    private OrderStatus status;
    private List<OrderDetailsDto> orderDetails;
}
