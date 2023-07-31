package io.github.nibiroo.rest.dto;

import io.github.nibiroo.domain.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialsRegisterDTO {
    private String login;
    private String password;
    private UserRole role;
}
