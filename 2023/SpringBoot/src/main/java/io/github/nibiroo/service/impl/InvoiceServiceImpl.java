package io.github.nibiroo.service.impl;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.entity.Invoice;
import io.github.nibiroo.domain.entity.ItemInvoice;
import io.github.nibiroo.domain.entity.Product;
import io.github.nibiroo.domain.repository.CustomerRepository;
import io.github.nibiroo.domain.repository.InvoiceRepository;
import io.github.nibiroo.domain.repository.ItemInvoiceRepository;
import io.github.nibiroo.domain.repository.ProductRepository;
import io.github.nibiroo.exception.BusinessRoleException;
import io.github.nibiroo.rest.dto.InvoiceDTO;
import io.github.nibiroo.rest.dto.ItemInvoicesDTO;
import io.github.nibiroo.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ItemInvoiceRepository itemInvoiceRepository;

    @Override
    @Transactional
    public Invoice save(InvoiceDTO invoiceDTO) {

        Long idCustomer = invoiceDTO.getCustomer();

        Customer customer = customerRepository
                .findById(idCustomer)
                .orElseThrow(()-> new BusinessRoleException("There isn't customer with id " + idCustomer));

        Invoice invoice = new Invoice();
        invoice.setTotal(invoiceDTO.getTotal());
        invoice.setDate(LocalDate.now(ZoneId.of("UTC")));
        invoice.setCustomer(customer);

        List<ItemInvoice> itemInvoiceList = convertItemsInvoice(invoice, invoiceDTO.getItemsInvoices());
        invoiceRepository.save(invoice);
        itemInvoiceRepository.saveAll(itemInvoiceList);
        invoice.setItemInvoices(itemInvoiceList);

        return invoice;
    }

    @Override
    public List<InvoiceDTO> getAllInvoicesFind(InvoiceDTO filter) {
        return null;
    }

    public List<ItemInvoice> convertItemsInvoice(Invoice invoice, List<ItemInvoicesDTO> itemInvoicesDTOList) {
        if (itemInvoicesDTOList.isEmpty()) {
            throw new BusinessRoleException("Cannot is possible make a invoice without items!");
        }

        return itemInvoicesDTOList
                .stream()
                .map(itemInvoicesDTO-> {
                    Product product = productRepository.findById(invoice.getId())
                                                        .orElseThrow(()-> new BusinessRoleException("There isn't product with id " + invoice.getId()));

                    ItemInvoice itemInvoice = new ItemInvoice();
                    itemInvoice.setInvoice(invoice);
                    itemInvoice.setAmount(itemInvoicesDTO.getAmount());
                    itemInvoice.setProduct(product);
                    return itemInvoice;
                })
                .collect(Collectors.toList());
    }
}
