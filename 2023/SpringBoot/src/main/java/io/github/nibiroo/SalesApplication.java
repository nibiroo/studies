package io.github.nibiroo;


import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// ANNOTATIONS
//   SpringBootApplication: Means this Java class is an SpringBoot application
//   RestController: Means the application can control and send messages to the browser
@SpringBootApplication
@RestController
public class SalesApplication {
    @Bean
    public CommandLineRunner init( @Autowired CustomersRepository customersRepository ){
        return args -> {
            System.out.println("Inserting new customers...");
            customersRepository.save(new Customer("Guilherme"));
            customersRepository.save(new Customer("Maria Carolina"));

            List<Customer> allCustomers = customersRepository.getAllCustomers();
            allCustomers.forEach(System.out::println);

            System.out.println("Updating and getting all customers...");
            allCustomers.forEach(c -> {
                c.setName(c.getName().concat(" test"));
                customersRepository.update(c);
            });

            allCustomers = customersRepository.getAllCustomers();
            allCustomers.forEach(System.out::println);

            System.out.println("Getting specific customer...");
            customersRepository.getCustomer("Carol").forEach(System.out::println);

            System.out.println("Deleting specific customer...");
            customersRepository.getCustomer("lin").forEach(customersRepository::delete);

            allCustomers = customersRepository.getAllCustomers();
            allCustomers.forEach(System.out::println);

//            System.out.println("Deleting all customers...");
//            customers.getAllCustomers().forEach(c -> {
//                customers.delete(c);
//            });
//            allCustomers = customers.getAllCustomers();
//            if (allCustomers.isEmpty()){
//                System.out.println("There are no customers records...");
//            } else { allCustomers.forEach(System.out::println); }
        };
    }

    // PSVM - Public Static Void Main, to create faster
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
