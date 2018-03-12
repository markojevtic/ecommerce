package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product load(@PathVariable("productId") String productId) {

        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product insert(@RequestBody Product product) {

        return null;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public Product update(@PathVariable("productId") String productId, @RequestBody Product product) {

        return null;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public Product delete(@PathVariable("productId") String productId) {

        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Product getAll() {

        return null;
    }
}
