package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import com.prodyna.ecommerce.server.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> load(final @PathVariable("productId") String productId) {

        final ProductDto productDto = this.productService.load(productId);
        return ResponseEntity.ok(productDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductDto> insert(final @RequestBody Product product) {

        final ProductDto productDto = this.productService.insert(product);
        return ResponseEntity.ok(productDto);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<ProductDto> update(final @RequestBody Product product) {

        final ProductDto productDto = this.productService.update(product);
        return ResponseEntity.ok(productDto);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(final @PathVariable("productId") String productId) {

        final ProductDto productDto = this.productService.delete(productId);
        return ResponseEntity.ok(productDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getAll() {

        final List<ProductDto> productDtoList = this.productService.getAll();
        return ResponseEntity.ok(productDtoList);
    }
}
