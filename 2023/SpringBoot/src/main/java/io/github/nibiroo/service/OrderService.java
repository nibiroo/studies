package io.github.nibiroo.service;

import io.github.nibiroo.domain.entity.Order;
import io.github.nibiroo.rest.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    Order save (OrderDTO orderDTO);

    List<OrderDTO> getAllOrdersFind(OrderDTO filter);
}
