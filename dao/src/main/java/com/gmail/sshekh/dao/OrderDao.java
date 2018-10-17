package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dao.model.OrderId;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findOrdersByUserId(Long id, int firstPosition, int maxOnPage);

    Long countAllOrders();

    List<Order> findAll(int firstPosition, int maxOnPage);

    Order getOrderByOrderId(OrderId id);
}
