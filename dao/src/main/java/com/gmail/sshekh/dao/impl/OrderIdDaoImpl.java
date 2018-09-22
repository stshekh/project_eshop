package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.OrderIdDao;
import com.gmail.sshekh.dao.model.OrderId;
import org.springframework.stereotype.Repository;

@Repository
public class OrderIdDaoImpl extends GenericDaoImpl<OrderId> implements OrderIdDao {
    public OrderIdDaoImpl() {
        super(OrderId.class);
    }
}
