package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dao.model.OrderId;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.OrderDTO;
import com.gmail.sshekh.service.dto.OrderIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("orderDTOConverter")
public class OrderDTOConverter implements DTOConverter<Order, OrderDTO> {
    @Autowired
    @Qualifier("orderIdDTOConverter")
    private DTOConverter<OrderId, OrderIdDTO> orderIdDTOConverter;

    @Override
    public OrderDTO toDTO(Order entity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderIdDTOConverter.toDTO(entity.getId()));
        //orderDTO.setUser(new UserDTOConverter().toDTO(entity.getUser()));
        //orderDTO.setItem(new ItemDTOConverter().toDTO(entity.getItem()));
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
