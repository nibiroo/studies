package io.github.nibiroo.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "itemOrder")
public class ItemInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idInvoice")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    private Integer amount;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Invoice getOrder() {
        return invoice;
    }
    public void setOrder(Invoice invoice) {
        this.invoice = invoice;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
