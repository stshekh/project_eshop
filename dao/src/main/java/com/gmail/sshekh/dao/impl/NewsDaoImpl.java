package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.NewsDao;
import com.gmail.sshekh.dao.model.News;

public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    public NewsDaoImpl(Class<News> clazz) {
        super(clazz);
    }
}
