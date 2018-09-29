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

import java.math.BigDecimal;
import java.util.*;

@Service
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
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Discount discount = discountConverter.toEntity(discountDTO);
            discountDao.create(discount);
            DiscountDTO discountDTONew = discountDTOConverter.toDTO(discount);
            transaction.commit();
            return discountDTONew;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
        return discountDTO;
    }

    @Override
    public Set<DiscountDTO> getDiscountOfRate(BigDecimal rate) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Discount> list = discountDao.getDiscountsByRate(rate);
            Set<DiscountDTO> discounts = new HashSet<>(discountDTOConverter.toDTOList(list));
            transaction.commit();
            return discounts;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
        return Collections.emptySet();
    }

    @Override
    public void update(DiscountDTO discountDTO) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Discount discount = discountConverter.toEntity(discountDTO);
            discountDao.update(discount);
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
    }

    @Override
    public Long getIdByRate(BigDecimal rate) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Long id = discountDao.getDiscountId(rate);
            transaction.commit();
            return id;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public DiscountDTO getRandomDiscount() {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Long ids = discountDao.countDiscounts();
            Long id = (long) (1 + random.nextInt(ids.intValue()));
            Discount discount = discountDao.getDiscountById(id);
            DiscountDTO discountDTO = discountDTOConverter.toDTO(discount);
            transaction.commit();
            return discountDTO;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        }
        return null;
    }


}
