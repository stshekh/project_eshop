package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.NewsDao;
import com.gmail.sshekh.dao.model.News;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    public NewsDaoImpl() {
        super(News.class);
    }
}
