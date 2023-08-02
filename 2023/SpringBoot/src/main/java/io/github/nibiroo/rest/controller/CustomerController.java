package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.repository.CustomerRepository;
import io.github.nibiroo.rest.dto.TokenDTO;
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
@RequestMapping({"/api/customers", "/api/customers/"})
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    @Operation(
            summary = "List all customers",
            description = "List all customer, can to use Filter",
            tags = { "Customer", "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") })})
    public List<Customer> getAllCustomersFind(Customer filter) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);

        return customerRepository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Register a new customer",
            description = "Register a new customer",
            tags = { "Customer", "POST" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") })})
    public Customer save (@RequestBody @Valid Customer customer) {
        return customerRepository.save(customer);
    }

    @DeleteMapping({"/{id}","{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete customer by id",
            description = "Delete customer by id",
            tags = { "Customer", "DELETE" })
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404")})
    public void delete (@PathVariable Long id) {
        customerRepository.findById(id)
                           .map( customer -> {
                               customerRepository.delete(customer);
                               return customer;
                           })
                           .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't customer with id " + id));
    }

    @PutMapping({"/{id}","{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Update customer by id",
            description = "Update customer by id",
            tags = { "Customer", "PUT" })
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404")})
    public void put(@PathVariable Long id, @RequestBody @Valid Customer customer) {

        customerRepository
                .findById(id)
                .map(custExist -> {
                    customer.setId(custExist.getId());
                    customerRepository.save(customer);
                    return custExist;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't customer with id " + id));
    }
}
