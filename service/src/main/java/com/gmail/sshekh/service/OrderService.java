package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.ItemDTO;
import com.gmail.sshekh.service.dto.OrderDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    void save(OrderDTO orderDTO, Long id);

    List<OrderDTO> findAll();

    void addOrdersToItemsAndUsers(BigDecimal fromAmount, BigDecimal toAmount, OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    Integer countAllOrders();

    List<OrderDTO> findAll(int firstPosition, int maxOnPage);

    OrderDTO getOrderByOrderId(Long idUser, Long idItem);

    void updateStatus(OrderDTO orderDTO, Long idUser, Long idItem);

    List<OrderDTO> getUsersOrders(int firstPosition, int maxOnPage);
}
