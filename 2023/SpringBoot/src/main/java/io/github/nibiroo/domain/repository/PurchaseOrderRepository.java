package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByCustomer(Customer customer);

    @Query(value = "select po.* from purchase_order po left join fetch po.itemPurchaseOrder where po.id = :id", nativeQuery = true)
    Optional<PurchaseOrder> findByIdFetchItemPurchaseOrder(@Param("id") Long id);
}
