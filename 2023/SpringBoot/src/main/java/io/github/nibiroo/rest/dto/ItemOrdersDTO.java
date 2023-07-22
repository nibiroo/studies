package io.github.nibiroo.rest.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemOrdersDTO {

    private Long idProduct;
    private Long amount;
}
