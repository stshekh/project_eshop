package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.model.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> findOrdersByUserId(Long id) {
        String hql = "FROM Order AS o WHERE o.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (List<Order>) query.list();
    }
}
