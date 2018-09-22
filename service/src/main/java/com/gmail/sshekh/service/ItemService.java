package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.ItemDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ItemService {
    ItemDTO save(ItemDTO itemDTO);

    ItemDTO update(ItemDTO itemDTO);

    List<ItemDTO> findAll();

    void delete(ItemDTO itemDTO);

    Set<ItemDTO> showItems(BigDecimal rate);

    void setDiscountsToItems(BigDecimal discount, BigDecimal formPrice, BigDecimal toPrice);

    Set<ItemDTO> showItemsOfPrice(BigDecimal fromPrice, BigDecimal toPrice);

    ItemDTO getItemOfPrice(BigDecimal fromPrice, BigDecimal toPrice);
}
