package io.github.nibiroo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/logout").permitAll();

                            authorizeConfig.requestMatchers("/api/products/**").hasRole("USER");
                            authorizeConfig.requestMatchers("/api/customers/**").hasRole("USER");
                            authorizeConfig.requestMatchers("/api/purchase-order/**").hasRole("USER");

                            authorizeConfig.anyRequest().authenticated();
                        }
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                                            .username("nibiro")
                                            .password(passwordEncoder().encode("123"))
                                            .roles("USER")
                                            .build();
        UserDetails admin = User.builder()
                                        .username("admin")
                                        .password(passwordEncoder().encode("admin"))
                                        .roles("ADMIN")
                                        .build();

        return new InMemoryUserDetailsManager(userDetails, admin);
    }
}
