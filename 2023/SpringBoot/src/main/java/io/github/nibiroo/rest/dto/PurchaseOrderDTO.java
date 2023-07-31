package io.github.nibiroo.rest.dto;

import io.github.nibiroo.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseOrderDTO {
    @NotNull(message = "{field.code-customer.required}")
    private Long idCustomer;
    @NotNull(message = "{field.total-purchase-order.required}")
    private BigDecimal total;
    @NotEmptyList(message = "{field.items-purchase-order.required}")
    private List<ItemPurchaseOrdersDTO> itemPurchaseOrders;
}
