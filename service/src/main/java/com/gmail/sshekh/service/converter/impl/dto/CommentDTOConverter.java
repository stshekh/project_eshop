package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.CommentDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("commentDTOConverter")
public class CommentDTOConverter implements DTOConverter<Comment, CommentDTO> {
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<User, UserDTO> userDTOConverter;

    @Override
    public CommentDTO toDTO(Comment entity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getIdComment());
        commentDTO.setContent(entity.getContent());
        commentDTO.setCreated(entity.getCreated());
        commentDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
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
