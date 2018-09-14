package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.ItemService;
import com.gmail.sshekh.converter.impl.dto.ItemDTOConverter;
import com.gmail.sshekh.converter.impl.entity.ItemConverter;
import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private ItemDTOConverter itemDTOConverter = new ItemDTOConverter();
    private ItemConverter itemConverter = new ItemConverter();

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemConverter.toEntity(itemDTO);
            itemDao.create(item);
            ItemDTO itemDTONew = itemDTOConverter.toDTO(item);
            transaction.commit();
            return itemDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save item!");
        }
        return itemDTO;
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemConverter.toEntity(itemDTO);
            itemDao.update(item);
            ItemDTO itemDTONew = itemDTOConverter.toDTO(item);
            transaction.commit();
            return itemDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update item!");
        }
        return itemDTO;
    }

    @Override
    public List<ItemDTO> findAll() {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findAll();
            List<ItemDTO> itemDTOS = itemDTOConverter.toDTOList(items);
            transaction.commit();
            return itemDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get all items");
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemConverter.toEntity(itemDTO);
            itemDao.delete(item);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete item");
        }
    }
}
