package com.gmail.sshekh.converter.impl.dto;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dto.OrderDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderDTOConverter implements DTOConverter<Order, OrderDTO> {
    @Override
    public OrderDTO toDTO(Order entity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(new OrderIdDTOConverter().toDTO(entity.getId()));
        orderDTO.setUser(new UserDTOConverter().toDTO(entity.getUser()));
        orderDTO.setItem(new ItemDTOConverter().toDTO(entity.getItem()));
        orderDTO.setCreated(entity.getCreated());
        orderDTO.setQuantity(entity.getQuantity());
        return orderDTO;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<OrderDTO> toDTOSet(Set<Order> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
