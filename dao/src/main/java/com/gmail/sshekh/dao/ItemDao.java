package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDao extends GenericDao<Item> {
    List<Item> getItemsInRange(BigDecimal from, BigDecimal to);

    List<Item> getItemOfRate(Long id);

    Long countItemsInRange(BigDecimal fromPrice, BigDecimal toPrice);
}
