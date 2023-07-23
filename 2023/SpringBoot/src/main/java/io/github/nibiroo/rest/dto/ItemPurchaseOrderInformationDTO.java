package io.github.nibiroo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPurchaseOrderInformationDTO {
    private String descriptionProduct;
    private BigDecimal unitPrice;
    private Long amount;
}
