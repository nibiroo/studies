package io.github.nibiroo;

import io.github.nibiroo.model.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

// ANNOTATIONS
// @Configurantion -> Means that the mentioned class is a configuration
// @Profiles("nameprofile") -> Means that the mentioned configuration is for a specific profile
// @Development -> Annotation
//@Configuration
//@Profile("development")
@Development
public class MyConfiguration {

    // For each configuration, it's used @Bean annotation
    @Bean(name = "applicationNameParam")
    public String applicationName(){
        return "<h3>Sales system!</h3>";
    }

    // CommandLineRunner -> When the build application happens, SpringBoot will find all @Bean annotations that are CommandLineRunner, compiling code within args
    @Bean(name = "executeConfigForProfile")
    public CommandLineRunner executeConfigForProfile(){
        return args -> {
            System.out.println("RUNNING DEVELOPMENT CONFIGURANTION!");
        };
    }
}