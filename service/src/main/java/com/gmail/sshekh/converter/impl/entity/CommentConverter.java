package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.dto.CommentDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentConverter implements Converter<CommentDTO, Comment> {
    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setIdComment(dto.getId());
        comment.setCreated(dto.getCreated());
        comment.setContent(dto.getContent());
        comment.setUser(new UserConverter().toEntity(dto.getUser()));
        return comment;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<Comment> toEntitySet(Set<CommentDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
