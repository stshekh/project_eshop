package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.dao.model.OrderId;
import com.gmail.sshekh.service.dto.OrderIdDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("orderIdDTOConverter")
public class OrderIdDTOConverter implements DTOConverter<OrderId, OrderIdDTO> {
    @Override
    public OrderIdDTO toDTO(OrderId entity) {
        OrderIdDTO orderIdDTO = new OrderIdDTO();
        orderIdDTO.setItemId(entity.getItemId());
        orderIdDTO.setUserId(entity.getUserId());
        return orderIdDTO;
    }

    @Override
    public List<OrderIdDTO> toDTOList(List<OrderId> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<OrderIdDTO> toDTOSet(Set<OrderId> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
