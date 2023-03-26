package io.github.nibiroo.rest.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemInvoicesDTO {

    private Long product;
    private Long amount;
}
