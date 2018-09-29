package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.dao.model.News;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.CommentDTO;
import com.gmail.sshekh.service.dto.NewsDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("newsDTOConverter")
public class NewsDTOConverter implements DTOConverter<News, NewsDTO> {
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<User, UserDTO> userDTOConverter;
    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter<Comment, CommentDTO> commentDTOConverter;

    @Override
    public NewsDTO toDTO(News entity) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(entity.getIdNews());
        newsDTO.setTitle(entity.getTitle());
        newsDTO.setContent(entity.getContent());
        newsDTO.setCreated(entity.getCreated());
        newsDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
        newsDTO.setComments(commentDTOConverter.toDTOSet(entity.getComments()));
        return newsDTO;
    }

    @Override
    public List<NewsDTO> toDTOList(List<News> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<NewsDTO> toDTOSet(Set<News> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
