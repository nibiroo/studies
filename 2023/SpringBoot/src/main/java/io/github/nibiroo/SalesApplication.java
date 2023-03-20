package io.github.nibiroo;


import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.entity.Invoice;
import io.github.nibiroo.domain.repository.CustomersRepository;
import io.github.nibiroo.domain.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// ANNOTATIONS
//   SpringBootApplication: Means this Java class is an SpringBoot application
//   RestController: Means the application can control and send messages to the browser
@SpringBootApplication
@RestController
public class SalesApplication {
    @Bean
    public CommandLineRunner init(@Autowired CustomersRepository customersRepository, @Autowired InvoiceRepository invoiceRepository ){
        return args -> {
            System.out.println();
            System.out.println();
            //
            System.out.println("Inserting new customers...");
            Customer customer = new Customer("Guilherme");
            customersRepository.save(customer);

            List<Customer> allCustomers = customersRepository.findAll();
            allCustomers.forEach(System.out::println);
            //
            System.out.println("-----");
            //
            System.out.println("Inserting new invoice...");
            Invoice invoice = new Invoice();
            invoice.setCustomer(customer);
            invoice.setDate(LocalDate.now());
            invoice.setTotal(BigDecimal.valueOf(100));

            invoiceRepository.save(invoice);
            //
            System.out.println("-----");
            //
            System.out.println("Printing customer with invoice");
            Customer customerTest = customersRepository.findCustomerFetchInvoices(customer.getId());
            System.out.println(customerTest);
            System.out.println(customerTest.getInvoices());
        };
    }

    // PSVM - Public Static Void Main, to create faster
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
