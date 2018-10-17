package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dao.model.OrderId;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> findOrdersByUserId(Long id, int firstPosition, int maxOnPage) {
        String hql = "FROM Order AS o WHERE o.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(firstPosition);
        query.setMaxResults(maxOnPage);
        query.setParameter("id", id);
        return (List<Order>) query.list();
    }

    @Override
    public Long countAllOrders() {
        String hql = "SELECT COUNT(*) FROM Order AS o";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Order> findAll(int firstPosition, int maxOnPage) {
        String hql = "FROM Order AS o ORDER BY o.created DESC";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(firstPosition);
        query.setMaxResults(maxOnPage);
        return query.list();
    }

    @Override
    public Order getOrderByOrderId(OrderId id) {
        String hql = "FROM Order AS o WHERE o.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Order) query.uniqueResult();
    }

}
