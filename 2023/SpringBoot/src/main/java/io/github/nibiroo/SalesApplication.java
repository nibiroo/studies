package io.github.nibiroo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// ANNOTATIONS
//   SpringBootApplication: Means this Java class is an SpringBoot application
//   ComponentScan: Means where is to search the configurations for this application
//   RestController: Means the application can control and send messages to the browser
@SpringBootApplication
/*@ComponentScan(
        basePackages = {
                        "io.github.nibiroo.repository",
                        "io.github.nibiroo.service"
                        }
)*/
@RestController
public class SalesApplication {

    // Qualifier is identifying application name and setting in variable private applicationName
    @Autowired
    @Qualifier("nomeAplicacao")
    private String applicationName;

    // GetMapping is a definition for when url is equal to parameter, to do the function
    @GetMapping("/hello")
    public String helloWorld(){
        return applicationName;
    }
    
    // PSVM - Public Static Void Main, to create faster
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
