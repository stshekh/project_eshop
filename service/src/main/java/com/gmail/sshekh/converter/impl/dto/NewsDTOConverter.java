package com.gmail.sshekh.converter.impl.dto;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.News;
import com.gmail.sshekh.dto.NewsDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NewsDTOConverter implements DTOConverter<News, NewsDTO> {
    @Override
    public NewsDTO toDTO(News entity) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(entity.getIdNews());
        newsDTO.setTitle(entity.getTitle());
        newsDTO.setContnet(entity.getContent());
        newsDTO.setCreated(entity.getCreated());
        newsDTO.setUser(new UserDTOConverter().toDTO(entity.getUser()));
        newsDTO.setComments(new CommentDTOConverter().toDTOSet(entity.getComments()));
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
