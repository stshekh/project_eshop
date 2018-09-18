package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.DiscountService;
import com.gmail.sshekh.converter.impl.dto.DiscountDTOConverter;
import com.gmail.sshekh.converter.impl.entity.DiscountConverter;
import com.gmail.sshekh.dao.model.Discount;
import com.gmail.sshekh.dao.DiscountDao;
import com.gmail.sshekh.dto.DiscountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiscountServiceImpl implements DiscountService {

    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);
    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
    private DiscountDTOConverter discountDTOConverter = new DiscountDTOConverter();
    private DiscountConverter discountConverter = new DiscountConverter();

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
    public Set<DiscountDTO> getDiscountOfRate(Integer rate) {
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
    public Long getIdByRate(Integer rate) {
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
}
