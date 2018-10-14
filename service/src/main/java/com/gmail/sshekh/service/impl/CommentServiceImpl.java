package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.CommentDao;
import com.gmail.sshekh.dao.NewsDao;
import com.gmail.sshekh.dao.UserDao;
import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.dao.model.User;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter<Comment, CommentDTO> commentDTOConverter;
    @Autowired
    @Qualifier("commentConverter")
    private Converter<CommentDTO, Comment> commentConverter;

    @Override
    public CommentDTO save(CommentDTO commentDTO, Long idNews, Long idUser) {
        Comment comment = commentConverter.toEntity(commentDTO);
        comment.setCreated(LocalDateTime.now());
        comment.setNews(newsDao.findOne(idNews));
        Set<Comment> comments = new HashSet<>();
        comments.add(comment);
        User user = userDao.findUserById(idUser);
        user.setComments(comments);
        commentDao.create(comment);
        //comment.setNews(commentDTO.getNews());
        return commentDTOConverter.toDTO(comment);
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        Comment comment = commentConverter.toEntity(commentDTO);
        commentDao.update(comment);
        return commentDTOConverter.toDTO(comment);
    }

    @Override
    public List<CommentDTO> findAll() {
        List<Comment> comments = commentDao.findAll();
        return commentDTOConverter.toDTOList(comments);
    }

    @Override
    public void delete(Long id) {
        commentDao.deleteById(id);
    }

    @Override
    public Set<CommentDTO> getCommentsByNewsId(Long id, int startPosition, int maxResult) {
        int firstPosition;
        if (startPosition > 1)
            firstPosition = (startPosition - 1) * maxResult;
        else firstPosition = 0;
        List<Comment> comments = commentDao.getCommentsByNewsId(id, firstPosition, maxResult);
        return new HashSet<>(commentDTOConverter.toDTOList(comments));
    }

    @Override
    public Integer countCommentsPerNews(Long id) {
        return commentDao.countCommentsPerNews(id).intValue();
    }
}
