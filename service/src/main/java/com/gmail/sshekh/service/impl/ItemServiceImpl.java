package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.service.ItemService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.DiscountDTO;
import com.gmail.sshekh.service.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    @Autowired
    private ItemDao itemDao;
    @Autowired
    @Qualifier("itemDTOConverter")
    private DTOConverter<Item, ItemDTO> itemDTOConverter;
    @Autowired
    @Qualifier("itemConverter")
    private Converter<ItemDTO, Item> itemConverter;
    @Autowired
    private DiscountServiceImpl discountService;
    private Random random = new Random();

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
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public Set<ItemDTO> showItems(BigDecimal rate) {
        Long id = discountService.getIdByRate(rate);
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
            int index = random.nextInt(itemDTOS.size());
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
    public void setDiscountsToItems(BigDecimal rate, BigDecimal fromPrice, BigDecimal toPrice) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Set<DiscountDTO> discounts = discountService.getDiscountOfRate(rate);
            Set<ItemDTO> items = showItemsOfPrice(fromPrice, toPrice);
            for (DiscountDTO discount : discounts) {
                discount.setItems(items);
                discountService.update(discount);
            }
            for (ItemDTO item : items) {
                item.setDiscounts(discounts);
                update(item);
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
