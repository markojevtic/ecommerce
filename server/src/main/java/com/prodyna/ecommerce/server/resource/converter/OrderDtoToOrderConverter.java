package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.repository.entity.Order;
import com.prodyna.ecommerce.server.repository.entity.OrderDetails;
import com.prodyna.ecommerce.server.repository.entity.OrderStatus;
import com.prodyna.ecommerce.server.resource.dto.OrderDetailsDto;
import com.prodyna.ecommerce.server.resource.dto.OrderDto;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDtoToOrderConverter extends ContextAwareConverter implements Converter<OrderDto, Order> {

    TypeDescriptor typeDscOrderDetails = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(OrderDetails.class));
    TypeDescriptor typeDscOrderDetailsDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(OrderDetailsDto.class));

    @Override
    public Order convert(OrderDto order) {
        return Order.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .customer(conversionService().convert(order.getCustomer(), Customer.class))
                .orderStatus(order.getStatus())
                .orderDetails((List<OrderDetails>) conversionService().convert(order.getOrderDetails(), typeDscOrderDetailsDto, typeDscOrderDetails))
                .build();
    }
}
