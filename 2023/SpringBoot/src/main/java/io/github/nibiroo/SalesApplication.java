package io.github.nibiroo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

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
