package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.OrderService;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.ItemDTO;
import com.gmail.sshekh.service.dto.OrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<Order, OrderDTO> orderDTOConverter;
    @Autowired
    private ItemServiceImpl itemService;

    @Override
    public void save(OrderDTO orderDTO) {
        //TODO test either it works or not
        Item item = itemDao.findOne(orderDTO.getItem().getId());
        User user = userDao.findOne(orderDTO.getUser().getId());
        Order order = new Order(user, item);
        order.setQuantity(orderDTO.getQuantity());
        order.setCreated(orderDTO.getCreated());
        user.getOrders().add(order);
        item.getOrders().add(order);
        userDao.update(user);
        itemDao.update(item);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = orderDao.findAll();
        return orderDTOConverter.toDTOList(orders);
    }

    @Override
    public void addOrdersToItemsAndUsers(BigDecimal fromAmount, BigDecimal toAmount, OrderDTO orderDTO) {
        ItemDTO item = itemService.getItemOfPrice(fromAmount, toAmount);
        Long amount = itemDao.countItemsInRange(fromAmount, toAmount);
        orderDTO.setQuantity(amount);
        orderDTO.setItem(item);
        update(orderDTO);
    }

    @Override
    public void update(OrderDTO orderDTO) {
        Item item = itemDao.findOne(orderDTO.getItem().getId());
        User user = userDao.findOne(orderDTO.getUser().getId());
        Order order = new Order(user, item);
        order.setQuantity(orderDTO.getQuantity());
        order.setCreated(orderDTO.getCreated());
        user.getOrders().add(order);
        order.getUser().getOrders().add(order);
        userDao.update(user);
    }
}
