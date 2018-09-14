package com.gmail.sshekh;

import com.gmail.sshekh.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO save(ItemDTO itemDTO);

    ItemDTO update(ItemDTO itemDTO);

    List<ItemDTO> findAll();

    void delete(ItemDTO itemDTO);
}
