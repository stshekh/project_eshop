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
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.sshekh.service.utils.UsersLoginUtil.getLoggedInUser;

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
    public CommentDTO save(CommentDTO commentDTO, Long idNews) {
        Comment comment = commentConverter.toEntity(commentDTO);
        comment.setCreated(LocalDateTime.now());
        comment.setNews(newsDao.findOne(idNews));
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        UserPrincipal userPrincipal = getLoggedInUser();
        User user = userDao.findUserById(userPrincipal.getId());
        user.setComments(comments);
        commentDao.create(comment);
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
    public List<CommentDTO> getCommentsByNewsId(Long id, int startPosition, int maxResult) {
        int firstPosition;
        if (startPosition > 1)
            firstPosition = (startPosition - 1) * maxResult;
        else firstPosition = 0;
        List<Comment> comments = commentDao.getCommentsByNewsId(id, firstPosition, maxResult);
        List<CommentDTO> commentDTOS = commentDTOConverter.toDTOList(comments);
        for (CommentDTO comment : commentDTOS) {
            comment.setUsername(userDao.getUserNameByCommentId(comment.getId()));
        }
        return commentDTOS;
    }

    @Override
    public Integer countCommentsPerArticle(Long id) {
        return commentDao.countCommentsPerArticle(id).intValue();
    }

    @Override
    public void deleteAllCommentsFromArticle(Long id) {
        List<Comment> comments = commentDao.getCommentsPerArticle(id);
        for (Comment comment : comments) {
            delete(comment.getIdComment());
        }
    }
}
