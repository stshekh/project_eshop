package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.OrderIdDao;
import com.gmail.sshekh.dao.model.OrderId;
import com.gmail.sshekh.service.OrderIdService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.OrderIdDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class OrderIdServiceImpl implements OrderIdService {
    private static final Logger logger = LogManager.getLogger(OrderIdServiceImpl.class);
    @Autowired
    private OrderIdDao orderIdDao;
    @Autowired
    @Qualifier("orderIdConverter")
    private Converter<OrderIdDTO, OrderId> orderIdConverter;
    @Autowired
    @Qualifier("orderIdDTOConverter")
    private DTOConverter<OrderId, OrderIdDTO> orderIdDTOConverter;

    @Override
    public OrderIdDTO save(OrderIdDTO orderIdDTO) {
        OrderId orderId = orderIdConverter.toEntity(orderIdDTO);
        orderIdDao.create(orderId);
        return orderIdDTOConverter.toDTO(orderId);

    }

    @Override
    public OrderIdDTO update(OrderIdDTO orderIdDTO) {
        OrderId orderId = orderIdConverter.toEntity(orderIdDTO);
        orderIdDao.update(orderId);
        return orderIdDTOConverter.toDTO(orderId);
    }

    @Override
    public List<OrderIdDTO> findAll() {
        List<OrderId> orderIds = orderIdDao.findAll();
        return orderIdDTOConverter.toDTOList(orderIds);
    }

    @Override
    public void delete(OrderIdDTO orderIdDTO) {
        OrderId orderId = orderIdConverter.toEntity(orderIdDTO);
        orderIdDao.delete(orderId);
    }
}
