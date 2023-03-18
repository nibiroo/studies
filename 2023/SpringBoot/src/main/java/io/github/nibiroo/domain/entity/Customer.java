package io.github.nibiroo.domain.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "idCustomer")
    private Set<Order> orders;

    public Customer() {
    }
    public Customer(String nameCustomer){
        this.name = nameCustomer;
    }
    public Customer(Integer idCustomer, String nameCustomer) {
        this.id = idCustomer;
        this.name = nameCustomer;
    }

    // GETTERS & SETTERS - ALT + INS
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
