package io.github.nibiroo.api.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.repository.CustomersRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Customer>> getAllCustomersFind(Customer filter) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);

        List<Customer> customerList = customersRepository.findAll(example);

        return ResponseEntity.ok(customerList);

    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Customer> getCustomerById (@PathVariable Long id) {

        Optional<Customer> optionalCustomer = customersRepository.findById(id);

        if(optionalCustomer.isPresent()) {
            return new ResponseEntity<>(optionalCustomer.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Customer> saveCustomer (@RequestBody Customer customer) {
        Customer customersaved = customersRepository.save(customer);
        return new ResponseEntity<>(customersaved, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer (@PathVariable Long id) {

        Optional<Customer> optionalCustomer = customersRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            customersRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Customer> putCustomer(@PathVariable Long id, @RequestBody Customer customer) {

        Optional<Customer> optionalCustomer = customersRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customerExist = optionalCustomer.get();
            customerExist.setName(customer.getName());
            customersRepository.save(customerExist);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
