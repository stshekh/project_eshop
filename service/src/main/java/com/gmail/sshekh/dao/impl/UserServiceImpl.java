package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.converter.impl.entity.UserConverter;
import com.gmail.sshekh.converter.impl.dto.UserDTOConverter;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.UserService;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao = new UserDaoImpl(User.class);
    private UserDTOConverter userDTOConverter = new UserDTOConverter();
    private UserConverter userConverter = new UserConverter();

    @Override
    public UserDTO save(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            transaction.begin();
            User user = userConverter.toEntity(userDTO);
            userDao.create(user);
            UserDTO userDTONew = userDTOConverter.toDTO(user);
            transaction.commit();
            return userDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save user", e);
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
            User user = userConverter.toEntity(userDTO);
            userDao.update(user);
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
}
