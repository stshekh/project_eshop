package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Discount;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountDao extends GenericDao<Discount> {
    List<Discount> getDiscountsByRate(BigDecimal discount);

    Long getDiscountId(BigDecimal discount);

    Discount getDiscountById(Long id);

    Long countDiscounts();
}
