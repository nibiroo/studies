package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
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
    public Customer save (@RequestBody @Valid Customer customer) {
        return customerRepository.save(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id) {
        customerRepository.findById(id)
                           .map( customer -> {
                               customerRepository.delete(customer);
                               return customer;
                           })
                           .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't customer with id " + id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
