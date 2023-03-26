package io.github.nibiroo.service.impl;

import io.github.nibiroo.domain.repository.InvoiceRepository;
import io.github.nibiroo.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
}
