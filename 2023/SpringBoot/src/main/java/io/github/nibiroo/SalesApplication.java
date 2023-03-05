package io.github.nibiroo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

    // Qualifier is identifying application name(nomeAplicacao) and setting in variable private applicationName;
    //  With the use of configurations application.properties is possible to do same thing, example:
    /*
        @Autowired
        @Qualifier("applicationNameParam")
        private String applicationName;
     */
    @Dog
    private Animal animal;
    @Bean(name = "executeAnimal")
    public CommandLineRunner execute(){
        return args -> {
            this.animal.makeNoise();
        };
    }

    @Value("${application.name}")
    private String applicationName;
    // GetMapping is a definition for when url is equal to parameter, to do the function
    @GetMapping("/hello")
    public String helloWorld(){
        return "<h3>"+this.applicationName+"</h3>";
    }
    
    // PSVM - Public Static Void Main, to create faster
    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
