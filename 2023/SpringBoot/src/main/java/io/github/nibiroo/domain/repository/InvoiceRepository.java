package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByCustomer(Customer customer);
}
