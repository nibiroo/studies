package io.github.nibiroo.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceDTO {

    private Long customer;
    private BigDecimal total;
    private List<ItemInvoicesDTO> itemsInvoices;
}
