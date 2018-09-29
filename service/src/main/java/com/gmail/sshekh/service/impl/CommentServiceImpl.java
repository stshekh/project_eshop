package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.CommentDao;
import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.service.CommentService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.CommentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    @Autowired
    private CommentDao commentDao;
    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter<Comment, CommentDTO> commentDTOConverter;
    @Autowired
    @Qualifier("commentConverter")
    private Converter<CommentDTO, Comment> commentConverter;

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
