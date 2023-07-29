package io.github.nibiroo.config;

import io.github.nibiroo.security.jwt.JwtAuthFilter;
import io.github.nibiroo.security.jwt.JwtService;
import io.github.nibiroo.service.impl.AuthenticationUserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationUserLoginServiceImpl userLoginService;
    private final JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, userLoginService);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       AuthenticationUserLoginServiceImpl userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder())
                    .and()
                    .build();
    }

    @Bean
    public HttpSecurity securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/logout").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.POST, "/api/users/").permitAll();

                            authorizeConfig.requestMatchers("/api/products/**").hasRole("ADMIN");

                            authorizeConfig.requestMatchers("/api/customers/**").hasAnyRole("USER", "ADMIN");
                            authorizeConfig.requestMatchers("/api/purchase-order/**").hasAnyRole("USER", "ADMIN");

                            authorizeConfig.anyRequest().authenticated();
                        }
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}

/*
* Referências:
*               https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/
*               https://www.dio.me/articles/spring-security-6-o-que-temos-de-novo
* */