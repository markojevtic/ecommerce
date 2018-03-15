package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Order;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.OrderDto;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import com.prodyna.ecommerce.server.services.CustomerService;
import com.prodyna.ecommerce.server.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_UTF8_VALUE)
public class OrderResource {

    @Autowired(required = false)
    private OrderService orderService;

    @Autowired
    private ConversionService conversionService;

    public static final ControllerLinkBuilder createLink() {
        return linkTo(OrderResource.class);
    }

    public static final ControllerLinkBuilder createSingleLink(String id) {
        return linkTo(methodOn(OrderResource.class).load(id));
    }

    public static ControllerLinkBuilder createCustomerOrdersLink(String id) {
        return linkTo(methodOn(OrderResource.class).getCustomerOrders(id));
    }

    @RequestMapping(method = GET, path = "/customer/{id}")
    public ResponseEntity<OrderDto> getCustomerOrders(@PathVariable String customerId) {
        return ResponseEntity.ok(null); // zamislis da zove servis i da nesto pametno dovuce
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<OrderDto> load(@PathVariable String id) {
        Order order = orderService.load(id);
        OrderDto orderDto = conversionService.convert(order, OrderDto.class);

        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<OrderDto>> getAll() {
        List<Order> orders = orderService.getAll();

        TypeDescriptor typeDscOrders = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Order.class));
        TypeDescriptor typeDscOrdersDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(OrderDto.class));

        List<OrderDto> ordersDtos = (List<OrderDto>) conversionService.convert(orders, typeDscOrders, typeDscOrdersDto);

        return ResponseEntity.ok(ordersDtos);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<OrderDto> insert(@RequestBody OrderDto orderDto) {
        Order order = conversionService.convert(orderDto, Order.class);
        Order insertedOrder = orderService.insert(order);

        return ResponseEntity.ok(conversionService.convert(insertedOrder, OrderDto.class));
    }

    @RequestMapping(method = POST, path = "/update")
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto) {
        Order order = conversionService.convert(orderDto, Order.class);
        Order updatedOrder = orderService.update(order);

        return ResponseEntity.ok(conversionService.convert(updatedOrder, OrderDto.class));
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
