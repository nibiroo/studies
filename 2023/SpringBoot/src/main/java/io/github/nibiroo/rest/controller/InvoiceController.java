package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.Invoice;
import io.github.nibiroo.rest.dto.InvoiceDTO;
import io.github.nibiroo.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody InvoiceDTO invoiceDTO) {

        Invoice invoice = invoiceService.save(invoiceDTO);
        return invoice.getId();
    }
}
