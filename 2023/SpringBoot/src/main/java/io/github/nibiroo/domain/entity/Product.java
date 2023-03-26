package io.github.nibiroo.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unit_price;

    public void setId(Long id) { this.id = id; }
    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getUnitPrice() {
        return unit_price;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unit_price = unitPrice;
    }
}
