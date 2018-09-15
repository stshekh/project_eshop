package com.gmail.sshekh;

import com.gmail.sshekh.dto.OrderDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void save(OrderDTO orderDTO);

    List<OrderDTO> findAll();

    void addOrderUsingSum(BigDecimal fromAmount, BigDecimal toAmount);
}
