package io.github.nibiroo.rest.controller;

import io.github.nibiroo.domain.entity.Order;
import io.github.nibiroo.rest.dto.OrderDTO;
import io.github.nibiroo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody OrderDTO orderDTO) {

        Order order = orderService.save(orderDTO);
        return order.getId();
    }
}
