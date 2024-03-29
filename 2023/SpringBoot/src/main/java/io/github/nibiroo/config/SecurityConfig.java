package io.github.nibiroo.config;

import io.github.nibiroo.domain.enums.UserRole;
import io.github.nibiroo.security.jwt.JwtAuthFilter;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttp -> {
                    authorizeHttp.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();

                    authorizeHttp.requestMatchers("/api/customers/**").hasRole(String.valueOf(UserRole.USER));
                    authorizeHttp.requestMatchers("/api/customers/**").hasRole(String.valueOf(UserRole.ADMIN));

                    authorizeHttp.requestMatchers("/api/products/**").permitAll();
                    authorizeHttp.requestMatchers("/swagger-ui.html", "/swagger-ui/*", "/v3/api-docs/**", "/v3/api-docs*").permitAll();
                    authorizeHttp.anyRequest().authenticated();
                });

        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/*
* Referências:
*               https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/
*               https://www.dio.me/articles/spring-security-6-o-que-temos-de-novo
*               https://youtube.com/watch?v=5w-YCcOjPD0 -> Principal
* */