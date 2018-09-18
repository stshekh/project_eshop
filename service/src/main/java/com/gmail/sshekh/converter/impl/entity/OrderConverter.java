package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dto.OrderDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderConverter implements Converter<OrderDTO, Order> {
    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(new OrderIdConverter().toEntity(dto.getId()));
        order.setUser(new UserConverter().toEntity(dto.getUser()));
        order.setItem(new ItemConverter().toEntity(dto.getItem()));
        order.setCreated(dto.getCreated());
        order.setQuantity(dto.getQuantity());
        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<Order> toEntitySet(Set<OrderDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
