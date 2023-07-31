package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.User;
import io.github.nibiroo.domain.repository.UserRepository;
import io.github.nibiroo.rest.dto.CredentialsDTO;
import io.github.nibiroo.rest.dto.CredentialsRegisterDTO;
import io.github.nibiroo.rest.dto.TokenDTO;
import io.github.nibiroo.security.jwt.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtService jwtService;

    public AuthorizationController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TokenDTO login(@RequestBody @Valid CredentialsDTO credentialsDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(credentialsDTO.getLogin(), credentialsDTO.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = jwtService.generateToken((User) auth.getPrincipal());

        return new TokenDTO(credentialsDTO.getLogin(), token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid CredentialsRegisterDTO registerDTO){
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        User newUser = new User(registerDTO.getLogin(), encryptedPassword, registerDTO.getRole());

        return this.userRepository.save(newUser);
    }
}
