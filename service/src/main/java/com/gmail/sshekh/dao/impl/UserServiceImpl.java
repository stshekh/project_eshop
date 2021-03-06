package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.converter.impl.dto.OrderDTOConverter;
import com.gmail.sshekh.converter.impl.entity.OrderConverter;
import com.gmail.sshekh.converter.impl.entity.UserConverter;
import com.gmail.sshekh.converter.impl.dto.UserDTOConverter;
import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.UserService;
import com.gmail.sshekh.dao.model.Order;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.dto.DiscountDTO;
import com.gmail.sshekh.dto.OrderDTO;
import com.gmail.sshekh.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao = new UserDaoImpl(User.class);
    private UserDTOConverter userDTOConverter = new UserDTOConverter();
    private UserConverter userConverter = new UserConverter();
    private OrderDao orderDao = new OrderDaoImpl(Order.class);
    private OrderDTOConverter orderDTOConverter = new OrderDTOConverter();

    @Override
    public UserDTO save(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userConverter.toEntity(userDTO);
            userDao.create(user);
            UserDTO userDTONew = userDTOConverter.toDTO(user);
            transaction.commit();
            return userDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error(e.getMessage(), e);
        }
        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<OrderDTO> orderDTOS = userDTO.getOrders();
            List<Order> orders = new OrderConverter().toEntityList(orderDTOS);
            User user = userConverter.toEntity(userDTO);
            user.setOrders(orders);
            userDao.update(user);
            //TODO order user converter correctly
            UserDTO userDTONew = userDTOConverter.toDTO(user);
            transaction.commit();
            return userDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update user", e);
        }
        return userDTO;
    }

    @Override
    public void delete(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userConverter.toEntity(userDTO);
            userDao.delete(user);
            transaction.commit();
            logger.info("User " + user.getFirstName() + " was successfully deleted!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("User wasn't deleted!", e);
        }
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User savedUser = userDao.findUserByEmail(email);
            UserDTO userDTO = userDTOConverter.toDTO(savedUser);
            transaction.commit();
            return userDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to fetch users", e);
        }
        return null;

    }

    @Override
    public List<UserDTO> findAll() {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<User> users = userDao.findAll();
            List<UserDTO> userDTOS = userDTOConverter.toDTOList(users);
            transaction.commit();
            return userDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to fetch users", e);
        }
        return Collections.emptyList();
    }

    public void setDiscount(UserDTO userDTO) {
        DiscountDTO discountDTO = new DiscountServiceImpl().getRandomDiscount();
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            userDTO.setDiscountDTO(discountDTO);
            User user = userConverter.toEntity(userDTO);
            userDao.update(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
    }

    @Override
    public void setOrders(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Long id = userDTO.getId();
            List<Order> orders = orderDao.findOrdersByUserId(id);
            List<OrderDTO> orderDTOS = orderDTOConverter.toDTOList(orders);
            userDTO.setOrders(orderDTOS);
            new UserServiceImpl().update(userDTO);
            if (transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.commit();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
    }
}
