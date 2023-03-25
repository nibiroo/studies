package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
