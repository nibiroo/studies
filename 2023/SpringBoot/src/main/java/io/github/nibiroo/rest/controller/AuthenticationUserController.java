package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.AuthenticationUser;
import io.github.nibiroo.exception.InvalidPasswordException;
import io.github.nibiroo.rest.dto.CredentialsDTO;
import io.github.nibiroo.rest.dto.TokenDTO;
import io.github.nibiroo.security.jwt.JwtService;
import io.github.nibiroo.service.impl.AuthenticationUserLoginServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/users", "/api/users/"})
@RequiredArgsConstructor
@Slf4j
public class AuthenticationUserController {
    private final AuthenticationUserLoginServiceImpl authenticationUserLoginService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationUser save(@RequestBody @Valid AuthenticationUser user ) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        log.info(authenticationUserLoginService.save(user).toString());
        return authenticationUserLoginService.save(user);
    }

    @PostMapping({"/auth", "/auth/"})
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentialsDTO) {
        try{
            AuthenticationUser authenticationUser = AuthenticationUser
                                                                .builder()
                                                                .login(credentialsDTO.getLogin())
                                                                .password(credentialsDTO.getPassword())
                                                                .build();
            UserDetails authenticatedUser = authenticationUserLoginService
                                                            .authenticate(authenticationUser);

            String token = jwtService.generateToken(authenticationUser);

            return new TokenDTO(authenticationUser.getLogin(), token);
        } catch (UsernameNotFoundException | InvalidPasswordException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

}
