package io.github.nibiroo.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(length = 11)
    private String cpf;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Invoice> invoices;

    public Customer() {
    }
    public Customer(String nameCustomer){
        this.name = nameCustomer;
    }
    public Customer(Long idCustomer, String nameCustomer, String cpf) {
        this.id = idCustomer;
        this.name = nameCustomer;
        this.cpf = cpf;
    }

    // GETTERS & SETTERS - ALT + INS
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }
    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
