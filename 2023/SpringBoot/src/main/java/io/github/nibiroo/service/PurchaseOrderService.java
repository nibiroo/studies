package io.github.nibiroo.service;

import io.github.nibiroo.domain.entity.PurchaseOrder;
import io.github.nibiroo.domain.enums.PurchaseOrderStatus;
import io.github.nibiroo.rest.dto.PurchaseOrderDTO;

import java.util.Optional;

public interface PurchaseOrderService {
    PurchaseOrder save (PurchaseOrderDTO purchaseOrderDTO);
    Optional<PurchaseOrder> getPurchaseOrderComplete(Long id);
    void updateStatus(Long id, PurchaseOrderStatus purchaseOrderStatus);
}
