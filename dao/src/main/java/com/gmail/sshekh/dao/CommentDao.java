package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {
    List<Comment> getCommentsByNewsId(Long id);
}
