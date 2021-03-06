package com.gmail.sshekh.converter.impl.dto;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.dto.CommentDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentDTOConverter implements DTOConverter<Comment, CommentDTO> {
    @Override
    public CommentDTO toDTO(Comment entity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getIdComment());
        commentDTO.setContent(entity.getContent());
        commentDTO.setCreated(entity.getCreated());
        commentDTO.setUser(new UserDTOConverter().toDTO(entity.getUser()));
        return commentDTO;
    }

    @Override
    public List<CommentDTO> toDTOList(List<Comment> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<CommentDTO> toDTOSet(Set<Comment> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
