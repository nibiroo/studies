package io.github.nibiroo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ANNOTATIONS
//   SpringBootApplication: Means this Java class is an SpringBoot application
@SpringBootApplication
public class SalesApplication {

    // PSVM - Public Static Void Main, to create faster
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
