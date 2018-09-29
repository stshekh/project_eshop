package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.dto.DiscountDTO;
import com.gmail.sshekh.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("discountConverter")
public class DiscountConverter implements Converter<DiscountDTO, Discount> {

    @Autowired
    @Qualifier("itemConverter")
    private Converter<ItemDTO, Item> itemConverter;

    @Override
    public Discount toEntity(DiscountDTO dto) {
        Discount discount = new Discount();
        discount.setId(dto.getId());
        discount.setName(dto.getName());
        discount.setRate(dto.getRate());
        discount.setExpDate(dto.getExpTime());
        discount.setItems(itemConverter.toEntitySet(dto.getItems()));
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
