package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.DiscountDao;
import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.service.DiscountService;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.DiscountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);
    @Autowired
    private DiscountDao discountDao;
    @Autowired
    @Qualifier("discountDTOConverter")
    private DTOConverter<Discount, DiscountDTO> discountDTOConverter;
    @Autowired
    @Qualifier("discountConverter")
    private Converter<DiscountDTO, Discount> discountConverter;
    private Random random = new Random();

    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        Discount discount = discountConverter.toEntity(discountDTO);
        discountDao.create(discount);
        return discountDTOConverter.toDTO(discount);
    }

    @Override
    public Set<DiscountDTO> getDiscountOfRate(BigDecimal rate) {
        List<Discount> list = discountDao.getDiscountsByRate(rate);
        return new HashSet<>(discountDTOConverter.toDTOList(list));
    }

    @Override
    public void update(DiscountDTO discountDTO) {
        Discount discount = discountConverter.toEntity(discountDTO);
        discountDao.update(discount);
    }

    @Override
    public Long getIdByRate(BigDecimal rate) {
        return discountDao.getDiscountId(rate);
    }

    @Override
    public DiscountDTO getRandomDiscount() {
        Long ids = discountDao.countDiscounts();
        Long id = (long) (1 + random.nextInt(ids.intValue()));
        Discount discount = discountDao.getDiscountById(id);
        return discountDTOConverter.toDTO(discount);
    }
}
