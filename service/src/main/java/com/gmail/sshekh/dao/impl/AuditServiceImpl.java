package com.gmail.sshekh.dao.impl;

import com.gmail.sshekh.AuditService;
import com.gmail.sshekh.converter.impl.dto.AuditDTOConverter;
import com.gmail.sshekh.converter.impl.entity.AuditConverter;
import com.gmail.sshekh.dao.AuditDao;
import com.gmail.sshekh.dao.model.Audit;
import com.gmail.sshekh.dto.AuditDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);
    private AuditDao auditDao = new AuditDaoImpl(Audit.class);
    private AuditDTOConverter auditDTOConverter = new AuditDTOConverter();
    private AuditConverter auditConverter = new AuditConverter();

    @Override
    public AuditDTO save(AuditDTO auditDTO) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Audit audit = auditConverter.toEntity(auditDTO);
            auditDao.create(audit);
            AuditDTO auditDTONew = auditDTOConverter.toDTO(audit);
            transaction.commit();
            return auditDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot save audit", e);
        }
        return auditDTO;
    }

    @Override
    public AuditDTO update(AuditDTO auditDTO) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Audit audit = auditConverter.toEntity(auditDTO);
            auditDao.update(audit);
            AuditDTO auditDTONew = auditDTOConverter.toDTO(audit);
            transaction.commit();
            return auditDTONew;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot update audit", e);
        }
        return auditDTO;
    }

    @Override
    public List<AuditDTO> findAll() {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Audit> audits = auditDao.findAll();
            List<AuditDTO> auditDTOS = auditDTOConverter.toDTOList(audits);
            transaction.commit();
            return auditDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot Get all audits", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(AuditDTO auditDTO) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Audit audit = auditConverter.toEntity(auditDTO);
            auditDao.delete(audit);
            transaction.commit();
            logger.info("Aduit was deleted");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Cannot delete audit", e);
        }
    }
}
