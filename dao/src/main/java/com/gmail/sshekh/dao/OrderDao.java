package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findOrdersByUserId(Long id);
}
