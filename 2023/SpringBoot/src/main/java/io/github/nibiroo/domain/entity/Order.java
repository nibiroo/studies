package io.github.nibiroo.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "date_order")
    private LocalDate date;

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    private StatusOrder statusOrder;

    @OneToMany(mappedBy = "order")
    private List<ItemOrder> itemOrder;
}
