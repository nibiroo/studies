package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.ItemPurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPurchaseOrderRepository extends JpaRepository<ItemPurchaseOrder, Long> {
}
