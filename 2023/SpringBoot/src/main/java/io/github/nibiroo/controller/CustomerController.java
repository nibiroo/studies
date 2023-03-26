package io.github.nibiroo.controller;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.repository.CustomersRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomersRepository customersRepository;

    public CustomerController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @GetMapping(produces = "application/json")
    public List<Customer> getAllCustomersFind(Customer filter) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);

        return customersRepository.findAll(example);

    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer (@RequestBody Customer customer) {
        return customersRepository.save(customer);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer (@PathVariable Long id) {
        customersRepository.findById(id)
                           .map( customer -> {
                               customersRepository.delete(customer);
                               return customer;
                           })
                           .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't customer with id " + id));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCustomer(@PathVariable Long id, @RequestBody Customer customer) {

        customersRepository
                .findById(id)
                .map(custExist -> {
                    customer.setId(custExist.getId());
                    customersRepository.save(customer);
                    return custExist;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't customer with id " + id));
    }
}
