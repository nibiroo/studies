package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.AuthenticationUser;
import io.github.nibiroo.service.impl.AuthenticationUserLoginServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/users", "/api/users/"})
@RequiredArgsConstructor
@Slf4j
public class AuthenticationUserController {
    private final AuthenticationUserLoginServiceImpl authenticationUserLoginService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationUser save(@RequestBody @Valid AuthenticationUser user ) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        log.info(authenticationUserLoginService.save(user).toString());
        return authenticationUserLoginService.save(user);
    }
}
