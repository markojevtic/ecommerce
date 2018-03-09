package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import com.prodyna.ecommerce.server.services.CustomerService;
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
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping(value = "/customers", produces = APPLICATION_JSON_UTF8_VALUE)
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ConversionService conversionService;

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id) {
        Customer customer = customerService.get(id);

        CustomerDto customerDto = conversionService.convert(customer, CustomerDto.class);

        return ResponseEntity.ok(customerDto);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<Customer> customers = customerService.getAll();

        TypeDescriptor typeDscCustomer = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Customer.class));
        TypeDescriptor typeDscCustomerDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CustomerDto.class));

        List<CustomerDto> customerDtos = (List<CustomerDto>) conversionService.convert(customers, typeDscCustomer, typeDscCustomerDto);

        return ResponseEntity.ok(customerDtos);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        Customer customer = conversionService.convert(customerDto, Customer.class);
        Customer savedCustomer = customerService.save(customer);

        return ResponseEntity.ok(conversionService.convert(savedCustomer, CustomerDto.class));
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }

    public static final ControllerLinkBuilder createLink() {
        return linkTo( CustomerResource.class );
    }

    public static final ControllerLinkBuilder createSingleLink(Long id) {
        return linkTo( methodOn( CustomerResource.class ).get(id) );
    }

}
