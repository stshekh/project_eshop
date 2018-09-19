package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.OrderService;
import com.gmail.sshekh.converter.impl.dto.OrderDTOConverter;
import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.dto.ItemDTO;
import com.gmail.sshekh.dto.OrderDTO;
import com.gmail.sshekh.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao = new OrderDaoImpl(Order.class);
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private UserDao userDao = new UserDaoImpl(User.class);
    private OrderDTOConverter orderDTOConverter = new OrderDTOConverter();

    @Override
    public void save(OrderDTO orderDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemDao.findOne(orderDTO.getItem().getId());
            User user = userDao.findOne(orderDTO.getUser().getId());

            Order order = new Order(user, item);
            order.setQuantity(orderDTO.getQuantity());
            order.setCreated(orderDTO.getCreated());

            user.getOrders().add(order);
            order.getUser().getOrders().add(order);

            userDao.update(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<OrderDTO> findAll() {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Order> orders = orderDao.findAll();
            List<OrderDTO> orderDTOS = orderDTOConverter.toDTOList(orders);
            transaction.commit();
            return orderDTOS;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
        return Collections.emptyList();
    }

    @Override
    public void addOrderUsingSum(BigDecimal fromAmount, BigDecimal toAmount, UserDTO userDTO) {
        ItemDTO item = new ItemServiceImpl().getItemOfPrice(fromAmount, toAmount);
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Long amount = itemDao.countItemsInRange(fromAmount, toAmount);
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setItem(item);
            orderDTO.setQuantity(amount);
            orderDTO.setUser(userDTO);
            orderDTO.setCreated(LocalDateTime.now());
            new OrderServiceImpl().save(orderDTO);
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
    }
}
