package io.github.nibiroo.rest.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemInvoicesDTO {

    private Long idProduct;
    private Long amount;
}
