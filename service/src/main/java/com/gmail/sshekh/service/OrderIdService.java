package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.OrderIdDTO;

import java.util.List;

public interface OrderIdService {
    OrderIdDTO save(OrderIdDTO orderIdDTO);

    OrderIdDTO update(OrderIdDTO orderIdDTO);

    List<OrderIdDTO> findAll();

    void delete(OrderIdDTO orderIdDTO);
}
