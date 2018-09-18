package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Discount;

import java.util.List;
import java.util.Set;

public interface DiscountDao extends GenericDao<Discount> {
    List<Discount> getDiscountsByRate(Integer discount);

    Long getDiscountId(Integer discount);
}
