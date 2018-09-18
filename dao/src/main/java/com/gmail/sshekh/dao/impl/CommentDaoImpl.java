package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.CommentDao;
import com.gmail.sshekh.dao.model.Comment;

public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    public CommentDaoImpl(Class<Comment> clazz) {
        super(clazz);
    }
}
