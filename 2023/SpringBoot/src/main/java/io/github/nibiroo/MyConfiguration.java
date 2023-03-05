package io.github.nibiroo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// @Configurantion -> Means that the mentioned class is a configuration
//  @Profiles("nameprofile") -> Means that the mentioned configuration is for a specific profile
@Configuration
@Profile("development")
public class MyConfiguration {

    // For each configuration, it's used @Bean annotation
    @Bean(name = "applicationNameParam")
    public String applicationName(){
        return "<h3>Sales system!</h3>";
    }

    // CommandLineRunner -> When the build application happens, SpringBoot will find all @Bean annotations that are CommandLineRunner, compiling code within args
    @Bean
    public CommandLineRunner executeConfigForProfile(){
        return args -> {
            System.out.println("RUNNING DEVELOPMENT CONFIGURANTION!");
        };
    }
}