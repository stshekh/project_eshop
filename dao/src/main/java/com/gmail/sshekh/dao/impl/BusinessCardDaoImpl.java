package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.BusinessCardDao;
import com.gmail.sshekh.dao.model.BusinessCard;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessCardDaoImpl extends GenericDaoImpl<BusinessCard> implements BusinessCardDao {

    public BusinessCardDaoImpl() {
        super(BusinessCard.class);
    }


    @Override
    public List<BusinessCard> getBusinessCardsByUserId(Long id) {
        String hql = "FROM BusinessCard AS b where b.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.list();
    }
}
