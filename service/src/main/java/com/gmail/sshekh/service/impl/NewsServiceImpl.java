package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.NewsDao;
import com.gmail.sshekh.dao.model.News;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.NewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    @Autowired
    private NewsDao newsDao;
    @Autowired
    @Qualifier("newsDTOConverter")
    private DTOConverter<News, NewsDTO> newsDTOConverter;
    @Autowired
    @Qualifier("newsConverter")
    private Converter<NewsDTO, News> newsConverter;

    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        News news = newsConverter.toEntity(newsDTO);
        news.setCreated(LocalDateTime.now());
        newsDao.create(news);
        return newsDTOConverter.toDTO(news);
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        News news = newsConverter.toEntity(newsDTO);
        newsDao.update(news);
        return newsDTOConverter.toDTO(news);
    }

    @Override
    public List<NewsDTO> findAll() {
        List<News> news = newsDao.findAll();
        return newsDTOConverter.toDTOList(news);
    }

    @Override
    public void delete(Long id) {
        News news = newsDao.findOne(id);
        news.setUser(null);
        newsDao.delete(news);
    }

    @Override
    public NewsDTO findOne(Long id) {
        News news = newsDao.findOne(id);
        return newsDTOConverter.toDTO(news);
    }
}
