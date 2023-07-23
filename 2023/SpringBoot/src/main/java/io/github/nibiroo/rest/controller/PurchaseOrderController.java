package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.ItemPurchaseOrder;
import io.github.nibiroo.domain.entity.PurchaseOrder;
import io.github.nibiroo.rest.dto.ItemPurchaseOrderInformationDTO;
import io.github.nibiroo.rest.dto.PurchaseOrderDTO;
import io.github.nibiroo.rest.dto.PurchaseOrderInformationDTO;
import io.github.nibiroo.service.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase-order")
public class PurchaseOrderController {
    private PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {

        PurchaseOrder purchaseOrder = purchaseOrderService.save(purchaseOrderDTO);
        return purchaseOrder.getId();
    }

    @GetMapping("/{id}")
    public PurchaseOrderInformationDTO getById(@PathVariable Long id) {
        return purchaseOrderService
                .getPurchaseOrderComplete(id)
                .map(it -> convert(it))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase order not found with id " + id));
    }



    public PurchaseOrderInformationDTO convert(PurchaseOrder purchaseOrder) {
        return PurchaseOrderInformationDTO
                .builder()
                .code(purchaseOrder.getId())
                .purchaseOrderDate(purchaseOrder.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(purchaseOrder.getCustomer().getCpf())
                .nameCustomer(purchaseOrder.getCustomer().getName())
                .total(purchaseOrder.getTotal())
                .itemPurchaseOrderInformationDTOList(convert(purchaseOrder.getItemPurchaseOrder()))
                .build();
    }

    public List<ItemPurchaseOrderInformationDTO> convert(List<ItemPurchaseOrder> items){
        if(CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items
                .stream()
                .map(it -> ItemPurchaseOrderInformationDTO
                            .builder()
                            .descriptionProduct(it.getProduct().getDescription())
                            .unitPrice(it.getProduct().getUnitPrice())
                            .amount(it.getAmount())
                            .build()
                ).collect(Collectors.toList());
    }
}
