package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.model.Item;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {
    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }
}
