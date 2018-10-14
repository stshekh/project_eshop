package com.gmail.sshekh.dao;

import com.gmail.sshekh.dao.model.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {

    List<News> findAllNews(int startPosition, int maxResult);

    Long countAllNews();
}
