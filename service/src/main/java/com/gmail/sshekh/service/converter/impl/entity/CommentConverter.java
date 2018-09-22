package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.service.dto.CommentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
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
