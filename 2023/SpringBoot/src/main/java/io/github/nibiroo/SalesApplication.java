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

    // PSVM - Public Static Void Main, to create faster
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
