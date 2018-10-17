package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.ItemDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ItemService {

    ItemDTO save(ItemDTO itemDTO);

    ItemDTO update(ItemDTO itemDTO);

    List<ItemDTO> findAll();

    List<ItemDTO> findAll(int startPosition, int maxOnPage);

    void remove(Long id);

    Set<ItemDTO> showItems(BigDecimal rate);

    void setDiscountsToItems(BigDecimal discount, BigDecimal formPrice, BigDecimal toPrice);

    Set<ItemDTO> showItemsOfPrice(BigDecimal fromPrice, BigDecimal toPrice);

    ItemDTO getItemOfPrice(BigDecimal fromPrice, BigDecimal toPrice);

    Integer countAllItems();

    ItemDTO findOne(Long id);
}
