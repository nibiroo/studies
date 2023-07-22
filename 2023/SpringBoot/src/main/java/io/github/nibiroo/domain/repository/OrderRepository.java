package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);
}
