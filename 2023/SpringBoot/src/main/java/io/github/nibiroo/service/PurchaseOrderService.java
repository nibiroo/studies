package io.github.nibiroo.service;

import io.github.nibiroo.domain.entity.PurchaseOrder;
import io.github.nibiroo.rest.dto.PurchaseOrderDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {
    PurchaseOrder save (PurchaseOrderDTO purchaseOrderDTO);
    List<PurchaseOrderDTO> getAllPurchaseOrdersFind(PurchaseOrderDTO filter);
    Optional<PurchaseOrder> getPurchaseOrderComplete(Long id);
}
