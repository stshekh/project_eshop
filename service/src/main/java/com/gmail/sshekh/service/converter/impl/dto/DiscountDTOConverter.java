package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.DiscountDTO;
import com.gmail.sshekh.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("discountDTOConverter")
public class DiscountDTOConverter implements DTOConverter<Discount, DiscountDTO> {
    @Autowired
    @Qualifier("itemDTOConverter")
    private DTOConverter<Item, ItemDTO> itemDTOConverter;

    @Override
    public DiscountDTO toDTO(Discount entity) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(entity.getId());
        discountDTO.setExpTime(entity.getExpDate());
        discountDTO.setName(entity.getName());
        discountDTO.setRate(entity.getRate());
        discountDTO.setItems(itemDTOConverter.toDTOSet(entity.getItems()));
        return discountDTO;
    }

    @Override
    public List<DiscountDTO> toDTOList(List<Discount> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<DiscountDTO> toDTOSet(Set<Discount> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
