package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.impl.OrderIdDaoImpl;
import com.gmail.sshekh.service.OrderIdService;
import com.gmail.sshekh.service.converter.impl.dto.OrderIdDTOConverter;
import com.gmail.sshekh.service.converter.impl.entity.OrderIdConverter;
import com.gmail.sshekh.dao.OrderIdDao;
import com.gmail.sshekh.dao.model.OrderId;
import com.gmail.sshekh.service.dto.OrderIdDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderIdServiceImpl implements OrderIdService {
    private static final Logger logger = LogManager.getLogger(OrderIdServiceImpl.class);
    private OrderIdDao orderIdDao = new OrderIdDaoImpl();
    private OrderIdConverter orderIdConverter = new OrderIdConverter();
    private OrderIdDTOConverter orderIdDTOConverter = new OrderIdDTOConverter();

    @Override
    public OrderIdDTO save(OrderIdDTO orderIdDTO) {
        Session session = orderIdDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            OrderId orderId = orderIdConverter.toEntity(orderIdDTO);
            orderIdDao.create(orderId);
            OrderIdDTO orderIdDTONew = orderIdDTOConverter.toDTO(orderId);
            transaction.commit();
            return orderIdDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot save orderId");
        }
        return orderIdDTO;
    }

    @Override
    public OrderIdDTO update(OrderIdDTO orderIdDTO) {
        Session session = orderIdDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            OrderId orderId = orderIdConverter.toEntity(orderIdDTO);
            orderIdDao.update(orderId);
            OrderIdDTO orderIdDTONew = orderIdDTOConverter.toDTO(orderId);
            transaction.commit();
            return orderIdDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot update orderId");
        }
        return orderIdDTO;
    }

    @Override
    public List<OrderIdDTO> findAll() {
        Session session = orderIdDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<OrderId> orderIds = orderIdDao.findAll();
            List<OrderIdDTO> orderIdDTOS = orderIdDTOConverter.toDTOList(orderIds);
            transaction.commit();
            return orderIdDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot get all orderId");
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(OrderIdDTO orderIdDTO) {
        Session session = orderIdDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            OrderId orderId = orderIdConverter.toEntity(orderIdDTO);
            orderIdDao.delete(orderId);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot delete orderId");
        }
    }
}
