package io.github.nibiroo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderInformationDTO {
    private Long code;
    private String cpf;
    private String nameCustomer;
    private BigDecimal total;
    private String purchaseOrderDate;
    private String status;
    private List<ItemPurchaseOrderInformationDTO> itemPurchaseOrderInformationDTOList;
}
