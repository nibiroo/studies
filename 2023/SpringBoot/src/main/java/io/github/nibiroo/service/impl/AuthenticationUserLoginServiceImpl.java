package io.github.nibiroo.service.impl;

import io.github.nibiroo.domain.entity.AuthenticationUser;
import io.github.nibiroo.domain.repository.AuthenticationUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationUserLoginServiceImpl implements UserDetailsService {
    private PasswordEncoder encoder;
    private AuthenticationUserRepository authenticationUserRepository;

    public AuthenticationUserLoginServiceImpl(PasswordEncoder encoder, AuthenticationUserRepository authenticationUserRepository) {
        this.encoder = encoder;
        this.authenticationUserRepository = authenticationUserRepository;
    }

    @Transactional
    public AuthenticationUser save(AuthenticationUser user) {
        return authenticationUserRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationUser authenticatedUserLogin = authenticationUserRepository.findByLogin(username)
                                    .orElseThrow(() -> new UsernameNotFoundException("User not found with login " + username));
        String[] roles = authenticatedUserLogin.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(authenticatedUserLogin.getLogin())
                .password(authenticatedUserLogin.getPassword())
                .roles(roles)
                .build();
    }
}
