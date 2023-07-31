package io.github.nibiroo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPurchaseOrdersDTO {
    private Long idProduct;
    private Long amount;
}
