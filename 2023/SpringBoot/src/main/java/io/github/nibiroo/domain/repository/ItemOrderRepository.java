package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {
}
