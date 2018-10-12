package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.CommentDao;
import com.gmail.sshekh.dao.model.Comment;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> getCommentsByNewsId(Long id) {
        String hql = "FROM Comment AS c WHERE c.news.idNews=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (List<Comment>) query.list();
    }
}
