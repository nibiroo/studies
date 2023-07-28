package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.Product;
import io.github.nibiroo.domain.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping({"/api/products", "/api/products/"})
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    public List<Product> getAllProductsFind(Product filter) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);

        return productRepository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct (@RequestBody @Valid Product product) {
        return productRepository.save(product);
    }

    @PutMapping({"/{id}","{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putProduct (@PathVariable Long id, @RequestBody @Valid Product product) {

        productRepository
                .findById(id)
                .map(prodExist -> {
                    prodExist.setDescription(product.getDescription());
                    prodExist.setUnitPrice(product.getUnitPrice());
                    productRepository.save(prodExist);
                    return prodExist;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't the product with id " + id));
    }

    @DeleteMapping({"/{id}","{id}"})
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
