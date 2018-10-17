package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.service.dto.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("itemDTOConverter")
public class ItemDTOConverter implements DTOConverter<Item, ItemDTO> {
    @Override
    public ItemDTO toDTO(Item entity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(entity.getId());
        itemDTO.setName(entity.getName());
        itemDTO.setDescription(entity.getDescription());
        itemDTO.setPrice(entity.getPrice());
        itemDTO.setUniqueNumber(entity.getUniqueNumber());
        return itemDTO;
    }

    @Override
    public List<ItemDTO> toDTOList(List<Item> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<ItemDTO> toDTOSet(Set<Item> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
