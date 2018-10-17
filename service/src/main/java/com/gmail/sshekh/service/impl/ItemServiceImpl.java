package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.ItemDao;
import com.gmail.sshekh.dao.OrderDao;
import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.service.DiscountService;
import com.gmail.sshekh.service.ItemService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.DiscountDTO;
import com.gmail.sshekh.service.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    @Qualifier("itemDTOConverter")
    private DTOConverter<Item, ItemDTO> itemDTOConverter;
    @Autowired
    @Qualifier("itemConverter")
    private Converter<ItemDTO, Item> itemConverter;
    @Autowired
    private DiscountService discountService;
    private Random random = new Random();

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        Item item = itemConverter.toEntity(itemDTO);
        item.setEnable(true);
        item.setUniqueNumber("F" + String.valueOf(LocalDateTime.now()));
        itemDao.create(item);
        return itemDTOConverter.toDTO(item);
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        Item item = itemConverter.toEntity(itemDTO);
        item.setEnable(true);
        itemDao.update(item);
        return itemDTOConverter.toDTO(item);
    }

    @Override
    public List<ItemDTO> findAll() {
        List<Item> items = itemDao.findAll();
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    public List<ItemDTO> findAll(int startPosition, int maxOnPage) {
        int firstPosition;
        if (startPosition > 1)
            firstPosition = (startPosition - 1) * maxOnPage;
        else firstPosition = 0;
        List<Item> items = itemDao.getAllItems(firstPosition, maxOnPage);
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    public void remove(Long id) {
        Item item = itemDao.findOne(id);
        item.setEnable(false);
        itemDao.update(item);
    }

    @Override
    public Set<ItemDTO> showItems(BigDecimal rate) {
        Long id = discountService.getIdByRate(rate);
        List<Item> items = new ArrayList<>(itemDao.getItemOfRate(id));
        return new HashSet<>(itemDTOConverter.toDTOList(items));
    }

    @Override
    public Set<ItemDTO> showItemsOfPrice(BigDecimal fromPrice, BigDecimal toPrice) {
        List<Item> list = itemDao.getItemsInRange(fromPrice, toPrice);
        return new HashSet<>(itemDTOConverter.toDTOList(list));
    }

    @Override
    public ItemDTO getItemOfPrice(BigDecimal fromPrice, BigDecimal toPrice) {
        List<Item> items = itemDao.getItemsInRange(fromPrice, toPrice);
        List<ItemDTO> itemDTOS = itemDTOConverter.toDTOList(items);
        int index = random.nextInt(itemDTOS.size());
        return itemDTOS.get(index);
    }

    @Override
    public Integer countAllItems() {
        return itemDao.countAllItems().intValue();
    }

    @Override
    public ItemDTO findOne(Long id) {
        return itemDTOConverter.toDTO(itemDao.findOne(id));
    }

    @Override
    public void delete(Long id) {
        if (orderDao.getOrderByItemId(id) == null) {
            itemDao.deleteById(id);
        }
    }

    @Override
    public void setDiscountsToItems(BigDecimal rate, BigDecimal fromPrice, BigDecimal toPrice) {
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
    }
}
