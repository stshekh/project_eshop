package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.OrderId;
import com.gmail.sshekh.dto.OrderIdDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderIdConverter implements Converter<OrderIdDTO, OrderId> {
    @Override
    public OrderId toEntity(OrderIdDTO dto) {
        OrderId orderId = new OrderId();
        orderId.setItemId(dto.getItemId());
        orderId.setUserId(dto.getUserId());
        return orderId;
    }

    @Override
    public List<OrderId> toEntityList(List<OrderIdDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<OrderId> toEntitySet(Set<OrderIdDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
