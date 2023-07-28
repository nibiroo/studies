package io.github.nibiroo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "authentication_user")
public class AuthenticationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotEmpty(message = "{field.login.required}")
    private String login;

    @Column
    @NotEmpty(message = "{field.password.required}")
    private String password;

    @Column
    private boolean admin;
}
