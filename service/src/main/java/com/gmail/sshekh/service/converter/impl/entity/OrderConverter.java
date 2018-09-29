package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dao.model.OrderId;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.dto.ItemDTO;
import com.gmail.sshekh.service.dto.OrderDTO;
import com.gmail.sshekh.service.dto.OrderIdDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("orderConverter")
public class OrderConverter implements Converter<OrderDTO, Order> {
    @Autowired
    @Qualifier("userConverter")
    private Converter<UserDTO, User> userConverter;
    @Autowired
    @Qualifier("itemConverter")
    private Converter<ItemDTO, Item> itemConverter;
    @Autowired
    @Qualifier("orderIdConverter")
    private Converter<OrderIdDTO, OrderId> orderIdConverter;

    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(orderIdConverter.toEntity(dto.getId()));
        order.setUser(userConverter.toEntity(dto.getUser()));
        order.setItem(itemConverter.toEntity(dto.getItem()));
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
