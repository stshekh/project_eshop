package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.dto.ItemDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemConverter implements Converter<ItemDTO, Item> {
    @Override
    public Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setUniqueNumber(dto.getUnqueNumber());
        return item;
    }

    @Override
    public List<Item> toEntityList(List<ItemDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<Item> toEntitySet(Set<ItemDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
