package io.github.nibiroo.service.impl;

import io.github.nibiroo.domain.entity.Customer;
import io.github.nibiroo.domain.entity.Order;
import io.github.nibiroo.domain.entity.ItemOrder;
import io.github.nibiroo.domain.entity.Product;
import io.github.nibiroo.domain.repository.CustomerRepository;
import io.github.nibiroo.domain.repository.OrderRepository;
import io.github.nibiroo.domain.repository.ItemOrderRepository;
import io.github.nibiroo.domain.repository.ProductRepository;
import io.github.nibiroo.exception.BusinessRoleException;
import io.github.nibiroo.rest.dto.OrderDTO;
import io.github.nibiroo.rest.dto.ItemOrdersDTO;
import io.github.nibiroo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ItemOrderRepository itemOrderRepository;

    @Override
    @Transactional
    public Order save(OrderDTO orderDTO) {

        Long idCustomer = orderDTO.getIdCustomer();

        Customer customer = customerRepository
                .findById(idCustomer)
                .orElseThrow(()-> new BusinessRoleException("There isn't customer with id " + idCustomer));

        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setDate(LocalDate.now(ZoneId.of("UTC")));
        order.setCustomer(customer);

        List<ItemOrder> itemOrderList = convertItemsOrder(order, orderDTO.getItemsOrders());
        orderRepository.save(order);
        itemOrderRepository.saveAll(itemOrderList);
        order.setItemOrder(itemOrderList);

        return order;
    }

    @Override
    public List<OrderDTO> getAllOrdersFind(OrderDTO filter) {
        return null;
    }

    public List<ItemOrder> convertItemsOrder(Order order, List<ItemOrdersDTO> itemOrdersDTOList) {
        if (itemOrdersDTOList.isEmpty()) {
            throw new BusinessRoleException("Cannot is possible make a order without items!");
        }

        return itemOrdersDTOList
                .stream()
                .map(itemOrdersDTO-> {
                    Product product = productRepository.findById(itemOrdersDTO.getIdProduct())
                                                        .orElseThrow(()-> new BusinessRoleException("There isn't product with id " + order.getId()));

                    ItemOrder itemOrder = new ItemOrder();
                    itemOrder.setOrder(order);
                    itemOrder.setAmount(itemOrdersDTO.getAmount());
                    itemOrder.setProduct(product);
                    return itemOrder;
                })
                .collect(Collectors.toList());
    }
}
