package io.github.nibiroo.service;

import io.github.nibiroo.domain.entity.Invoice;
import io.github.nibiroo.rest.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    Invoice save (InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAllInvoicesFind(InvoiceDTO filter);
}
