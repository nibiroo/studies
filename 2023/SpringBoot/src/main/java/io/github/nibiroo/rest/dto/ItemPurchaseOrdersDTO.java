package io.github.nibiroo.rest.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPurchaseOrdersDTO {
    private Long idProduct;
    private Long amount;
}
