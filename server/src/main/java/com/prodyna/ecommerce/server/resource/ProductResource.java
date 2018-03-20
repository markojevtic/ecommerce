package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.CategoryDto;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import com.prodyna.ecommerce.server.services.ProductService;
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
@RequestMapping(value = "/products", produces = APPLICATION_JSON_UTF8_VALUE)
public class ProductResource {

    @Autowired(required = false)
    private ProductService productService;

    @Autowired
    private ConversionService conversionService;

    public static final ControllerLinkBuilder createResourceLink() {
        return linkTo(ProductResource.class);
    }

    public static final ControllerLinkBuilder createResourceSingleLink(String id) {
        return linkTo(methodOn(ProductResource.class).load(id));
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<ProductDto> load(@PathVariable String id) {
        Product product = productService.load(id);
        ProductDto productDto = conversionService.convert(product, ProductDto.class);

        return ResponseEntity.ok(productDto);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> products = productService.getAll();

        TypeDescriptor typeDscProducts = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Product.class));
        TypeDescriptor typeDscProductsDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ProductDto.class));

        List<ProductDto> categoriesDtos = (List<ProductDto>) conversionService.convert(products, typeDscProducts, typeDscProductsDto);

        return ResponseEntity.ok(categoriesDtos);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<ProductDto> insert(@RequestBody ProductDto productDto) {
        Product product = conversionService.convert(productDto, Product.class);
        Product insertedProduct = productService.insert(product);

        return ResponseEntity.ok(conversionService.convert(insertedProduct, ProductDto.class));
    }

    @RequestMapping(method = POST, path = "/update")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto) {
        Product product = conversionService.convert(productDto, Product.class);
        Product updatedProduct = productService.update(product);

        return ResponseEntity.ok(conversionService.convert(updatedProduct, ProductDto.class));
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
