package io.github.nibiroo.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePurchaseOrderStatus {
    private String newStatus;
}
