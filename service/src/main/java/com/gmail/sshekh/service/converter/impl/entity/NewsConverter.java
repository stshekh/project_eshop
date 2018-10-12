package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.dao.model.Comment;
import com.gmail.sshekh.dao.model.News;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.dto.CommentDTO;
import com.gmail.sshekh.service.dto.NewsDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("newsConverter")
public class NewsConverter implements Converter<NewsDTO, News> {

    @Autowired
    @Qualifier("userConverter")
    private Converter<UserDTO, User> userConverter;
    @Autowired
    @Qualifier("commentConverter")
    private Converter<CommentDTO, Comment> commentConverter;

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setIdNews(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setUser(userConverter.toEntity(dto.getUser()));
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
