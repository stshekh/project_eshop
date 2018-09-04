package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.GenericDao;
import com.gmail.sshekh.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {
    private Class<T> clazz;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T findOne(long id) {
        return getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }

    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
