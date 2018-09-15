package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.dao.DiscountDao;
import org.hibernate.query.Query;

import java.util.List;

public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {
    public DiscountDaoImpl(Class<Discount> clazz) {
        super(clazz);
    }

    @Override
    public List<Discount> getDiscountsByRate(Integer discount) {
        String hql = "from Discount as d where d.rate=:discount";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("discount", discount);
        return (List<Discount>) query.list();
    }

    @Override
    public Long getDiscountId(Integer discount) {
        String hql = "select d.id from Discount as d where d.rate=:discount";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("discount", discount);
        return (Long) query.uniqueResult();
    }


}
