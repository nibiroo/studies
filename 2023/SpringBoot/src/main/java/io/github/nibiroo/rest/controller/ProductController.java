package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.entity.Product;
import io.github.nibiroo.domain.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "List all products",
            description = "List all products, can to use Filter",
            tags = { "Product", "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") })})
    public List<Product> getAllProductsFind(Product filter) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);

        return productRepository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Register a new product",
            description = "Register a new product",
            tags = { "Product", "POST" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") })})
    public Product saveProduct (@RequestBody @Valid Product product) {
        return productRepository.save(product);
    }

    @PutMapping({"/{id}","{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Update product by id",
            description = "Update product by id",
            tags = { "Product", "PUT" })
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404")})
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
    @Operation(
            summary = "Delete product by id",
            description = "Delete product by id",
            tags = { "Product", "DELETE" })
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404")})
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
