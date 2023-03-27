package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.repository.CustomerRepository;
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

    @GetMapping(produces = "application/json")
    public List<Customer> getAllCustomersFind(Customer filter) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);

        return customerRepository.findAll(example);

    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer (@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer (@PathVariable Long id) {
        customerRepository.findById(id)
                           .map( customer -> {
                               customerRepository.delete(customer);
                               return customer;
                           })
                           .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't customer with id " + id));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCustomer(@PathVariable Long id, @RequestBody Customer customer) {

        customerRepository
                .findById(id)
                .map(custExist -> {
                    customer.setId(custExist.getId());
                    customerRepository.save(customer);
                    return custExist;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "There isn't customer with id " + id));
    }
}
