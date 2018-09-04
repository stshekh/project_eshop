package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(Class<User> clazz) {
        super(clazz);
    }


    @Override
    public User findUserByEmail(String email) {
        String hql = "from User as U where U.email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setFirstName(resultSet.getString("FIRST_NAME"));
        user.setLastName(resultSet.getString("LAST_NAME"));
        user.setRole(resultSet.getLong("ID_ROLE"));
        return user;
    }
}
