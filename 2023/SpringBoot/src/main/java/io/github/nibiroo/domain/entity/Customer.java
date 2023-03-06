package io.github.nibiroo.domain.entity;

public class Customer {
    private Integer id;
    private String name;

    public Customer() {
    }
    public Customer(String name){
        this.name = name;
    }
    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // GETTERS & SETTERS - ALT + INS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
