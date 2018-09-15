package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.dto.DiscountDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountConverter implements Converter<DiscountDTO, Discount> {
    @Override
    public Discount toEntity(DiscountDTO dto) {
        Discount discount = new Discount();
        discount.setId(dto.getId());
        discount.setName(dto.getName());
        discount.setRate(dto.getRate());
        discount.setExpDate(dto.getExpTime());
        discount.setItems(new ItemConverter().toEntitySet(dto.getItems()));
        return discount;
    }

    @Override
    public List<Discount> toEntityList(List<DiscountDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<Discount> toEntitySet(Set<DiscountDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
