package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.NewsDao;
import com.gmail.sshekh.dao.model.News;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    public NewsDaoImpl() {
        super(News.class);
    }

    @Override
    public List<News> findAllNews(int startPosition, int maxResult) {
        String hql = "FROM News AS n ORDER BY n.created DESC";//TODO ask why it doesn't show news
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }

    @Override
    public Long countAllNews() {
        String hql = "SELECT COUNT(*) FROM News AS n";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }
}
