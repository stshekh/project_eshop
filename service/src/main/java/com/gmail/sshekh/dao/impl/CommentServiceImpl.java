package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.CommentService;
import com.gmail.sshekh.converter.impl.dto.CommentDTOConverter;
import com.gmail.sshekh.converter.impl.entity.CommentConverter;
import com.gmail.sshekh.dao.CommentDao;
import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.dto.CommentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private CommentDao commentDao = new CommentDaoImpl(Comment.class);
    private CommentDTOConverter commentDTOConverter = new CommentDTOConverter();
    private CommentConverter commentConverter = new CommentConverter();

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Comment comment = commentConverter.toEntity(commentDTO);
            commentDao.create(comment);
            CommentDTO commentDTONew = commentDTOConverter.toDTO(comment);
            transaction.commit();
            return commentDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save comment");
        }
        return commentDTO;
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Comment comment = commentConverter.toEntity(commentDTO);
            commentDao.update(comment);
            CommentDTO commentDTONew = commentDTOConverter.toDTO(comment);
            transaction.commit();
            return commentDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update comment");
        }
        return commentDTO;
    }

    @Override
    public List<CommentDTO> findAll() {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Comment> comments = commentDao.findAll();
            List<CommentDTO> commentDTOS = commentDTOConverter.toDTOList(comments);
            transaction.commit();
            return commentDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get al comments");
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(CommentDTO commentDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Comment comment = commentConverter.toEntity(commentDTO);
            commentDao.delete(comment);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Delete wasn't completed!");
        }
    }
}
