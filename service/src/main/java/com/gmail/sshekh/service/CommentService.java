package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO save(CommentDTO commentDTO);

    CommentDTO update(CommentDTO commentDTO);

    List<CommentDTO> findAll();

    void delete(CommentDTO commentDTO);
}
