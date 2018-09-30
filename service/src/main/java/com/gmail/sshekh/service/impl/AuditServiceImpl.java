package com.gmail.sshekh.service.impl;

import com.gmail.sshekh.dao.AuditDao;
import com.gmail.sshekh.dao.model.Audit;
import com.gmail.sshekh.service.AuditService;
import com.gmail.sshekh.service.converter.impl.dto.AuditDTOConverter;
import com.gmail.sshekh.service.converter.impl.entity.AuditConverter;
import com.gmail.sshekh.service.dto.AuditDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);
    @Autowired
    private AuditDao auditDao;
    @Autowired
    @Qualifier("auditDTOConverter")
    private AuditDTOConverter auditDTOConverter;
    @Autowired
    @Qualifier("auditConverter")
    private AuditConverter auditConverter;

    @Override
    public AuditDTO save(AuditDTO auditDTO) {
        Audit audit = auditConverter.toEntity(auditDTO);
        auditDao.create(audit);
        return auditDTOConverter.toDTO(audit);
    }

    @Override
    public AuditDTO update(AuditDTO auditDTO) {
        Audit audit = auditConverter.toEntity(auditDTO);
        auditDao.update(audit);
        return auditDTOConverter.toDTO(audit);
    }

    @Override
    public List<AuditDTO> findAll() {
        List<Audit> audits = auditDao.findAll();
        return auditDTOConverter.toDTOList(audits);
    }

    @Override
    public void delete(AuditDTO auditDTO) {
        Audit audit = auditConverter.toEntity(auditDTO);
        auditDao.delete(audit);
        logger.info("Aduit was deleted");
    }
}
