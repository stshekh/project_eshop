package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.dao.DiscountDao;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {
    public DiscountDaoImpl() {
        super(Discount.class);
    }

    @Override
    public List<Discount> getDiscountsByRate(BigDecimal discount) {
        String hql = "FROM Discount AS d WHERE d.rate=:discount";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("discount", discount);
        return (List<Discount>) query.list();
    }

    @Override
    public Long getDiscountId(BigDecimal discount) {
        String hql = "SELECT d.id FROM Discount AS d WHERE d.rate=:discount";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("discount", discount);
        return (Long) query.uniqueResult();
    }

    @Override
    public Discount getDiscountById(Long id) {
        String hql = "FROM Discount AS d WHERE d.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Discount) query.uniqueResult();
    }

    @Override
    public Long countDiscounts() {
        String hql = "SELECT COUNT(*) FROM Discount AS d";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }


}
