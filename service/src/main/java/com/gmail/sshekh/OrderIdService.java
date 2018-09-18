package com.gmail.sshekh;

import com.gmail.sshekh.dto.OrderIdDTO;

import java.util.List;

public interface OrderIdService {
    OrderIdDTO save(OrderIdDTO orderIdDTO);

    OrderIdDTO update(OrderIdDTO orderIdDTO);

    List<OrderIdDTO> findAll();

    void delete(OrderIdDTO orderIdDTO);
}
