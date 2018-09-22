package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.OrderDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void save(OrderDTO orderDTO);

    List<OrderDTO> findAll();

    void addOrdersToItemsAndUsers(BigDecimal fromAmount, BigDecimal toAmount, OrderDTO orderDTO);

    void update(OrderDTO orderDTO);
}
