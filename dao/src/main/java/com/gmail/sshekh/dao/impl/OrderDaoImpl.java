package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.model.Order;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }

    @Override
    public List<Order> findOrdersByUserId(Long id) {
        String hql = "FROM Order as o WHERE o.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (List<Order>) query.list();
    }
}
