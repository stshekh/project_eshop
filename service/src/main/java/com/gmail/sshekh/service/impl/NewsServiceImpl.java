package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.NewsDao;
import com.gmail.sshekh.dao.model.News;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.NewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
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
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            News news = newsConverter.toEntity(newsDTO);
            newsDao.create(news);
            NewsDTO newsDTONew = newsDTOConverter.toDTO(news);
            transaction.commit();
            return newsDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save news");
        }
        return newsDTO;
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            News news = newsConverter.toEntity(newsDTO);
            newsDao.update(news);
            NewsDTO newsDTONew = newsDTOConverter.toDTO(news);
            transaction.commit();
            return newsDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update news");
        }
        return newsDTO;
    }

    @Override
    public List<NewsDTO> findAll() {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<News> news = newsDao.findAll();
            List<NewsDTO> newsDTOS = newsDTOConverter.toDTOList(news);
            transaction.commit();
            return newsDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find all the News", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(NewsDTO newsDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            News news = newsConverter.toEntity(newsDTO);
            newsDao.delete(news);
            transaction.commit();

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete news", e);
        }
    }
}
