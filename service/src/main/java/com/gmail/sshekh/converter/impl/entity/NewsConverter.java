package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.News;
import com.gmail.sshekh.dto.NewsDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NewsConverter implements Converter<NewsDTO, News> {
    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setIdNews(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContnet());
        news.setCreated(dto.getCreated());
        news.setUser(new UserConverter().toEntity(dto.getUser()));
        news.setComments(new CommentConverter().toEntitySet(dto.getComments()));
        return news;
    }

    @Override
    public List<News> toEntityList(List<NewsDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<News> toEntitySet(Set<NewsDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
