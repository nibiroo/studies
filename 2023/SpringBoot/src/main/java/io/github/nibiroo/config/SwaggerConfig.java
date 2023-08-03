package io.github.nibiroo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApiConfiguration() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info()
                .title("API - Sales")
                .version("1.0")
                .description("API developed for the Spring Boot Expert course: JPA, RESTFul API, Security, JWT, and more.")
                .contact(contact());
    }

    private Contact contact() {
        return new Contact()
                        .name("Guilherme de Carvalho Machado")
                        .email("guilhermedecarvalhomachado@gmail.com")
                        .url("https://github.com/nibiroo");
    }
}