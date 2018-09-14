package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.OrderIdDao;
import com.gmail.sshekh.dao.model.OrderId;

public class OrderIdDaoImpl extends GenericDaoImpl<OrderId> implements OrderIdDao {
    public OrderIdDaoImpl(Class<OrderId> clazz) {
        super(clazz);
    }
}
