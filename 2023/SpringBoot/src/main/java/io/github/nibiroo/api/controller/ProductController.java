package io.github.nibiroo.api.controller;

import io.github.nibiroo.domain.entity.Product;
import io.github.nibiroo.domain.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping(produces = "application/json")
    public List<Product> getAllProductsFind(Product filter) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);

        return productRepository.findAll(example);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct (@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putProduct (@PathVariable Long id, @RequestBody Product product) {

        productRepository
                .findById(id)
                .map(prodExist -> {
                    prodExist.setDescription(product.getDescription());
                    prodExist.setUnitPrice(product.getUnitPrice());
                    productRepository.save(prodExist);
                    return prodExist;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't the product with id " + id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {

        productRepository
                .findById(id)
                .map(prodExist -> {
                    productRepository.delete(prodExist);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't the product with id " + id));
    }
}
