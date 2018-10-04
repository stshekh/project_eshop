package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        super(User.class);
    }


    @Override
    public User findUserByEmail(String email) {
        String hql = "FROM User AS U WHERE U.email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

    @Override
    public User findUserById(Long id) {
        String hql = "FROM User AS u WHERE u.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> findAll(int startPosition, int maxResult) {
        String hql = "FROM User AS U ORDER BY U.id asc";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }
}
