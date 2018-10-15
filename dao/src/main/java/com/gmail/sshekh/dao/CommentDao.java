package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {

    List<Comment> getCommentsByNewsId(Long id, int startPosition, int maxResult);

    Long countCommentsPerArticle(Long id);

    int deleteCommentsFromArticle(Long id);

    List<Comment> getCommentsPerArticle(Long id);

    String getUserNameByCommentId(Long id);
}
