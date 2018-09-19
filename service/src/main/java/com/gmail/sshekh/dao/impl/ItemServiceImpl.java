package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.ItemService;
import com.gmail.sshekh.converter.impl.dto.ItemDTOConverter;
import com.gmail.sshekh.converter.impl.entity.ItemConverter;
import com.gmail.sshekh.dao.DiscountDao;
import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.dto.DiscountDTO;
import com.gmail.sshekh.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.math.BigDecimal;
import java.util.*;

public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
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

    @Override
    public Set<ItemDTO> showItems(Integer rate) {
        Long id = new DiscountServiceImpl().getIdByRate(rate);
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = new ArrayList<>(itemDao.getItemOfRate(id));

            Set<ItemDTO> itemsList = new HashSet<>(itemDTOConverter.toDTOList(items));
            if (itemsList.isEmpty()) {
                return Collections.emptySet();
            }
            transaction.commit();
            return itemsList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptySet();
    }

    @Override
    public Set<ItemDTO> showItemsOfPrice(BigDecimal fromPrice, BigDecimal toPrice) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> list = itemDao.getItemsInRange(fromPrice, toPrice);
            Set<ItemDTO> items = new HashSet<>(itemDTOConverter.toDTOList(list));
            transaction.commit();
            return items;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
        return Collections.emptySet();
    }

    @Override
    public ItemDTO getItemOfPrice(BigDecimal fromPrice, BigDecimal toPrice) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }

            List<Item> items = itemDao.getItemsInRange(fromPrice, toPrice);
            List<ItemDTO> itemDTOS = itemDTOConverter.toDTOList(items);
            int index = 0 + (int) (Math.random() * itemDTOS.size());
            ItemDTO itemDTO = itemDTOS.get(index);
            transaction.commit();
            return itemDTO;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public void setDiscountsToItems(Integer rate, BigDecimal fromPrice, BigDecimal toPrice) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Set<DiscountDTO> discounts = new DiscountServiceImpl().getDiscountOfRate(rate);
            Set<ItemDTO> items = new ItemServiceImpl().showItemsOfPrice(fromPrice, toPrice);
            for (DiscountDTO discount : discounts) {
                discount.setItems(items);
                new DiscountServiceImpl().update(discount);
            }
            for (ItemDTO item : items) {
                item.setDisounts(discounts);
                new ItemServiceImpl().update(item);
            }
            if (transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
                transaction.commit();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
    }
}
