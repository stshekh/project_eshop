package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.model.Item;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {
    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public List<Item> getItemsInRange(BigDecimal from, BigDecimal to) {
        String hql = "FROM Item AS i WHERE i.price BETWEEN :fromPrice AND :toPrice";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("fromPrice", from);
        query.setParameter("toPrice", to);
        return (List<Item>) query.list();
    }


    @Override
    public List<Item> getItemOfRate(Long id) {
        String hql = "SELECT i FROM Item i JOIN i.discounts discounts WHERE discounts.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (List<Item>) query.list();

    }

    @Override
    public Long countItemsInRange(BigDecimal fromPrice, BigDecimal toPrice) {
        String hql = "SELECT COUNT(*) FROM Item AS i WHERE i.price BETWEEN :fromPrice AND :toPrice";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("fromPrice", fromPrice);
        query.setParameter("toPrice", toPrice);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long countAllItems() {
        String hql = "SELECT COUNT(*) FROM Item AS i WHERE i.enable IS TRUE";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Item> getAllItems(int startPosition, int maxResult) {
        String hql = "FROM Item AS i WHERE i.enable IS TRUE ORDER BY i.name ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }
}
