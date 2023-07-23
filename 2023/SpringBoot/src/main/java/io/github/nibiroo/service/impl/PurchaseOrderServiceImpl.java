package io.github.nibiroo.service.impl;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.entity.ItemPurchaseOrder;
import io.github.nibiroo.domain.entity.Product;
import io.github.nibiroo.domain.entity.PurchaseOrder;
import io.github.nibiroo.domain.enums.StatusPurchaseOrder;
import io.github.nibiroo.domain.repository.CustomerRepository;
import io.github.nibiroo.domain.repository.PurchaseOrderRepository;
import io.github.nibiroo.domain.repository.ItemPurchaseOrderRepository;
import io.github.nibiroo.domain.repository.ProductRepository;
import io.github.nibiroo.exception.BusinessRoleException;
import io.github.nibiroo.rest.dto.ItemPurchaseOrdersDTO;
import io.github.nibiroo.rest.dto.PurchaseOrderDTO;
import io.github.nibiroo.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ItemPurchaseOrderRepository itemPurchaseOrderRepository;

    @Override
    @Transactional
    public PurchaseOrder save(PurchaseOrderDTO purchaseOrderDTO) {

        Long idCustomer = purchaseOrderDTO.getIdCustomer();

        Customer customer = customerRepository
                .findById(idCustomer)
                .orElseThrow(()-> new BusinessRoleException("There isn't customer with id " + idCustomer));

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setTotal(purchaseOrderDTO.getTotal());
        purchaseOrder.setDate(LocalDate.now(ZoneId.of("UTC")));
        purchaseOrder.setCustomer(customer);
        purchaseOrder.setStatusPurchaseOrder(StatusPurchaseOrder.PLACED);

        List<ItemPurchaseOrder> itemOrderList = convertItemsOrder(purchaseOrder, purchaseOrderDTO.getItemPurchaseOrders());
        purchaseOrderRepository.save(purchaseOrder);
        itemPurchaseOrderRepository.saveAll(itemOrderList);
        purchaseOrder.setItemPurchaseOrder(itemOrderList);

        return purchaseOrder;
    }

    @Override
    public List<PurchaseOrderDTO> getAllPurchaseOrdersFind(PurchaseOrderDTO filter) {
        return null;
    }

    @Override
    public Optional<PurchaseOrder> getPurchaseOrderComplete(Long id) {
        return purchaseOrderRepository.findByIdFetchItemPurchaseOrder(id);
    }

    public List<ItemPurchaseOrder> convertItemsOrder(PurchaseOrder purchaseOrder, List<ItemPurchaseOrdersDTO> itemPurchaseOrdersDTOS) {
        if (itemPurchaseOrdersDTOS.isEmpty()) {
            throw new BusinessRoleException("Cannot is possible make a order without items!");
        }

        return itemPurchaseOrdersDTOS
                .stream()
                .map(itemOrdersDTO-> {
                    Product product = productRepository.findById(itemOrdersDTO.getIdProduct())
                                                        .orElseThrow(()-> new BusinessRoleException("There isn't product with id " + purchaseOrder.getId()));

                    ItemPurchaseOrder itemPurchaseOrder = new ItemPurchaseOrder();
                    itemPurchaseOrder.setPurchaseOrder(purchaseOrder);
                    itemPurchaseOrder.setAmount(itemOrdersDTO.getAmount());
                    itemPurchaseOrder.setProduct(product);
                    return itemPurchaseOrder;
                })
                .collect(Collectors.toList());
    }
}
