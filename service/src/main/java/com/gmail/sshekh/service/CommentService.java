package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.CommentDTO;

import java.util.List;
import java.util.Set;

public interface CommentService {
    CommentDTO save(CommentDTO commentDTO);

    CommentDTO update(CommentDTO commentDTO);

    List<CommentDTO> findAll();

    void delete(Long id);

    Set<CommentDTO> getCommentsByNewsId(Long id);
}
