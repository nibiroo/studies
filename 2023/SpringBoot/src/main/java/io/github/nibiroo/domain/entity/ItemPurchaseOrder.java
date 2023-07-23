package io.github.nibiroo.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "item_purchase_order")
public class ItemPurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_purchase_order")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    private Long amount;
}
