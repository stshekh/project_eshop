package com.gmail.sshekh.converter.impl.dto;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.dto.DiscountDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountDTOConverter implements DTOConverter<Discount, DiscountDTO> {
    @Override
    public DiscountDTO toDTO(Discount entity) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(entity.getId());
        discountDTO.setExpTime(entity.getExpDate());
        discountDTO.setName(entity.getName());
        discountDTO.setRate(entity.getRate());
        discountDTO.setItems(new ItemDTOConverter().toDTOSet(entity.getItems()));
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
