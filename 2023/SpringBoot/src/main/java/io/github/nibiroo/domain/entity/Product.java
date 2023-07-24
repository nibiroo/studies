package io.github.nibiroo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    @NotEmpty(message = "{field.description.required}")
    private String description;

    @Column(name = "unit_price")
    @NotNull(message = "{field.price.required}")
    private BigDecimal unitPrice;
}
