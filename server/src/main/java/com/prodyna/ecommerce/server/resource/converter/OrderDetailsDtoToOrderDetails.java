package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.OrderDetails;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.OrderDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsDtoToOrderDetails extends ContextAwareConverter implements Converter<OrderDetailsDto, OrderDetails>{

    @Override
    public OrderDetails convert(OrderDetailsDto orderDetailsDto) {
        return OrderDetails.builder()
                .ordinal(orderDetailsDto.getOrdinal())
                .product(conversionService().convert(orderDetailsDto.getProduct(), Product.class))
                .price(orderDetailsDto.getPrice())
                .quantity(orderDetailsDto.getQuantity())
                .amount(orderDetailsDto.getAmount())
                .build();
    }
}
