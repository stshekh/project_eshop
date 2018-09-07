package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.Role;
import com.gmail.sshekh.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;


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

    @Override
    public Role getRoleByEmail(String email) {
        String hql = "select role from User as U where U.email=:email";
        Query query=getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        return (Role) query.uniqueResult();
    }


}
