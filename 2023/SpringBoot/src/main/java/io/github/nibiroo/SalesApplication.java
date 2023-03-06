package io.github.nibiroo;


import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.repository.Customers;
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
    public CommandLineRunner init( @Autowired Customers customers ){
        return args -> {
            System.out.println("Inserting new customers...");
            customers.save(new Customer("Guilherme"));
            customers.save(new Customer("Maria Carolina"));

            List<Customer> allCustomers = customers.getAllCustomers();
            allCustomers.forEach(System.out::println);

            System.out.println("Updating and getting all customers...");
            allCustomers.forEach(c -> {
                c.setNome(c.getNome().concat(" test"));
                customers.update(c);
            });

            allCustomers = customers.getAllCustomers();
            allCustomers.forEach(System.out::println);

            System.out.println("Getting specific customer...");
            customers.getCustomer("Carol").forEach(System.out::println);

            System.out.println("Deleting specific customer...");
            customers.getCustomer("lin").forEach(customers::delete);

            allCustomers = customers.getAllCustomers();
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
