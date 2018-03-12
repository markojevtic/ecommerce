package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Product load(final @PathVariable("productId") String productId) {

        return this.productService.load(productId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product insert(final @RequestBody Product product) {

        return this.productService.insert(product);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public Product update(final @PathVariable("productId") String productId, final @RequestBody Product product) {

        return this.productService.update(product);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public void delete(final @PathVariable("productId") String productId) {

        this.productService.delete(productId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAll() {

        return this.productService.getAll();
    }
}
