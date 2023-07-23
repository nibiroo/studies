package io.github.nibiroo.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseOrderDTO {
    private Long idCustomer;
    private BigDecimal total;
    private List<ItemPurchaseOrdersDTO> itemPurchaseOrders;
}
