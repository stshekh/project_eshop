package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.CommentDTO;

import java.util.List;
import java.util.Set;

public interface CommentService {
    CommentDTO save(CommentDTO commentDTO, Long idNews, Long idUser);

    CommentDTO update(CommentDTO commentDTO);

    List<CommentDTO> findAll();

    void delete(Long id);

    List<CommentDTO> getCommentsByNewsId(Long id, int startPosition, int maxResult);

    Integer countCommentsPerArticle(Long id);

    void deleteAllCommentsFromArticle(Long id);
}
