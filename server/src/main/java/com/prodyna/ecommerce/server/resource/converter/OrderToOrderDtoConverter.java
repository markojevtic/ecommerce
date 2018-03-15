package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Order;
import com.prodyna.ecommerce.server.repository.entity.OrderDetails;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import com.prodyna.ecommerce.server.resource.dto.OrderDetailsDto;
import com.prodyna.ecommerce.server.resource.dto.OrderDto;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderToOrderDtoConverter extends ContextAwareConverter implements Converter<Order, OrderDto> {

    TypeDescriptor typeDscOrderDetails = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(OrderDetails.class));
    TypeDescriptor typeDscOrderDetailsDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(OrderDetailsDto.class));

    @Override
    public OrderDto convert(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .customer(conversionService().convert(order.getCustomer(), CustomerDto.class))
                .status(order.getOrderStatus())
                .orderDetails((List<OrderDetailsDto>) conversionService().convert(order.getOrderDetails(), typeDscOrderDetails, typeDscOrderDetailsDto))
                .build();
    }
}
