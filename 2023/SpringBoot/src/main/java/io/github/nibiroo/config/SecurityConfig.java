package io.github.nibiroo.config;

import io.github.nibiroo.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       UserServiceImpl userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder())
                    .and()
                    .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/logout").permitAll();

                            authorizeConfig.requestMatchers("/api/products/**").hasRole("ADMIN");

                            authorizeConfig.requestMatchers("/api/customers/**").hasAnyRole("USER", "ADMIN");
                            authorizeConfig.requestMatchers("/api/purchase-order/**").hasAnyRole("USER", "ADMIN");

                            authorizeConfig.anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}

/*
* Referências:
*               https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/
*               https://www.dio.me/articles/spring-security-6-o-que-temos-de-novo
* */