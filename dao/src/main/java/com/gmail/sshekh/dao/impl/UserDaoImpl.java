package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.BusinessCard;
import com.gmail.sshekh.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        String hql = "FROM User AS u ORDER BY u.firstName ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }

    @Override
    public Long getRoleIdByUserId(Long id) {
        String hql = "SELECT role.idRole FROM User u JOIN u.role role WHERE u.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Set<BusinessCard> getUsersBusinessCards(Long id) {
        String hql = "SELECT businessCards FROM User u WHERE u.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        Set<BusinessCard> businessCards = new HashSet<>(query.list());
        return businessCards;
    }

    @Override
    public Long countUsers() {
        String hql = "SELECT COUNT(*) FROM User AS u";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    public String getUserNameByCommentId(Long id) {
        String hql = "SELECT u.firstName FROM User u JOIN u.comments comment WHERE comment.idComment=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (String) query.uniqueResult();
    }
}
