package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByNameLike(String name);
}