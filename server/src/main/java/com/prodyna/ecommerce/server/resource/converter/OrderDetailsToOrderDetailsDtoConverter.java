package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.OrderDetails;
import com.prodyna.ecommerce.server.resource.dto.OrderDetailsDto;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsToOrderDetailsDtoConverter extends ContextAwareConverter implements Converter<OrderDetails, OrderDetailsDto>{

    @Override
    public OrderDetailsDto convert(OrderDetails orderDetails) {
        return OrderDetailsDto.builder()
                .ordinal(orderDetails.getOrdinal())
                .product(conversionService().convert(orderDetails.getProduct(), ProductDto.class))
                .price(orderDetails.getPrice())
                .quantity(orderDetails.getQuantity())
                .amount(orderDetails.getAmount())
                .build();
    }
}
