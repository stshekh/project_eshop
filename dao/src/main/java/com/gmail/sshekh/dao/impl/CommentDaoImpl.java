package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.CommentDao;
import com.gmail.sshekh.dao.model.Comment;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> getCommentsByNewsId(Long id, int startPosition, int maxResult) {
        String hql = "FROM Comment AS c WHERE c.news.idNews=:id ORDER BY c.created desc";//TODO ask Artem why order by doesn't work with DateTime
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }

    @Override
    public Long countCommentsPerNews(Long id) {
        String hql = "SELECT COUNT(*) FROM Comment AS c WHERE c.news.idNews=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Long) query.uniqueResult();
    }
}
