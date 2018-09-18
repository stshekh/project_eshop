package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.model.Order;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }
}
