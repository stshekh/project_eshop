package com.gmail.sshekh;

import com.gmail.sshekh.dto.OrderDTO;
import com.gmail.sshekh.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void save(OrderDTO orderDTO);

    List<OrderDTO> findAll();

    void addOrderUsingSum(BigDecimal fromAmount, BigDecimal toAmount, UserDTO userDTO);
}
