package io.github.nibiroo.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private Integer id;
    private Customer customer;
    private LocalDate date;
    private BigDecimal total;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Customer getClient() {
        return customer;
    }
    public void setClient(Customer customer) {
        this.customer = customer;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
