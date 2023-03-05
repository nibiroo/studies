package io.github.nibiroo.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private Integer id;
    private Client client;
    private LocalDate date;
    private BigDecimal total;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
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
