package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.model.Item;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {
    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public List<Item> getItemsInRange(BigDecimal from, BigDecimal to) {
        String hql = "from Item as i where i.price>:fromPrice AND i.price<:toPrice";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("fromPrice", from);
        query.setParameter("toPrice", to);
        return (List<Item>) query.list();
    }


    @Override
    public List<Item> getItemOfRate(Long id) {
        String hql = "select i from Item i JOIN i.discounts discounts where discounts.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (List<Item>) query.list();

    }

    @Override
    public Long countItemsInRange(BigDecimal fromPrice, BigDecimal toPrice) {
        String hql = "SELECT COUNT(*) FROM Item AS i WHERE i.price>:fromPrice AND i.price<:toPrice";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("fromPrice", fromPrice);
        query.setParameter("toPrice", toPrice);
        return (Long) query.uniqueResult();
    }


}
